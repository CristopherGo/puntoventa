package com.tienda.puntoventa.controller;

import com.tienda.puntoventa.model.Categoria;
import com.tienda.puntoventa.model.Producto;
import com.tienda.puntoventa.service.ProductoService;
import com.tienda.puntoventa.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "productos/lista";
    }

    /**retorna todos los productos en formato JSON 
    GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }
*/
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/formulario";
    }

   /** @PostMapping("/guardar")
    public String guardarProducto2(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }**/
    
    @PostMapping("/guardar")
public String guardarProducto(@ModelAttribute Producto producto) {
    // Recuperar la categoría completa por el ID
    Long categoriaId = producto.getCategoria().getId();
    Categoria categoria = categoriaService.obtenerCategoriaPorId(categoriaId);
    producto.setCategoria(categoria);

    productoService.guardarProducto(producto);
    return "redirect:/productos";
}
/* 
@PostMapping("/guardar")
    public Producto crearProducto(@RequestBody Producto producto) {
        Long categoriaId = producto.getCategoria().getId();
        Categoria categoria = categoriaService.obtenerCategoriaPorId(categoriaId);
        producto.setCategoria(categoria);
        return productoService.guardarProducto(producto);
    }
*/

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
}
