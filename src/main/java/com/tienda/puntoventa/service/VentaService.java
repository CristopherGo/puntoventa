package com.tienda.puntoventa.service;

import com.tienda.puntoventa.model.Venta;
import java.util.List;

public interface VentaService {
    List<Venta> listarVentas();
    Venta guardarVenta(Venta venta);
    Venta obtenerVentaPorId(Long id);
}
