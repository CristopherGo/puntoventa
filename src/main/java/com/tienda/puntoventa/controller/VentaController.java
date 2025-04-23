package com.tienda.puntoventa.controller;

import com.tienda.puntoventa.model.DetalleVenta;
import com.tienda.puntoventa.model.Producto;
import com.tienda.puntoventa.model.Venta;
import com.tienda.puntoventa.service.ProductoService;
import com.tienda.puntoventa.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ventas")
@SessionAttributes("carrito")
public class VentaController {

    private final VentaService ventaService;
    private final ProductoService productoService;

    public VentaController(VentaService ventaService, ProductoService productoService) {
        this.ventaService = ventaService;
        this.productoService = productoService;
    }

    @ModelAttribute("carrito")
    public List<DetalleVenta> carrito() {
        return new ArrayList<>();
    }

    @GetMapping
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        return "ventas/lista";
    }

    @GetMapping("/nueva")
    public String nuevaVenta(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "ventas/nueva";
    }

    @GetMapping("/carrito/eliminar/{index}")
    public String eliminarDelCarrito(@PathVariable int index, @ModelAttribute("carrito") List<DetalleVenta> carrito) {
        if (index >= 0 && index < carrito.size()) {
            carrito.remove(index);
        }
        return "redirect:/ventas/nueva";
    }

    @PostMapping("/agregar")
    public String agregarProducto(@RequestParam Long productoId, @RequestParam Integer cantidad,
            @ModelAttribute("carrito") List<DetalleVenta> carrito) {

        Producto producto = productoService.obtenerProductoPorId(productoId);
        if (producto != null && cantidad > 0 && cantidad <= producto.getStock()) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setSubtotal(producto.getPrecio() * cantidad);
            carrito.add(detalle);
        }
        return "redirect:/ventas/nueva";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute("carrito") List<DetalleVenta> carrito, SessionStatus status) {

        if (carrito.isEmpty())
            return "redirect:/ventas/nueva";

        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        double total = carrito.stream().mapToDouble(DetalleVenta::getSubtotal).sum();
        venta.setTotal(total);
        venta.setDetalles(carrito);

        for (DetalleVenta detalle : carrito) {
            detalle.setVenta(venta);
            // Actualizar stock
            Producto p = detalle.getProducto();
            p.setStock(p.getStock() - detalle.getCantidad());
            productoService.guardarProducto(p);
        }

        ventaService.guardarVenta(venta);
        status.setComplete(); // Limpiar carrito

        return "redirect:/ventas";
    }
}
