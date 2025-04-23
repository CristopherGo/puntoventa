package com.tienda.puntoventa.service;

import com.tienda.puntoventa.model.DetalleVenta;
import java.util.List;

public interface DetalleVentaService {
    List<DetalleVenta> listarDetalles();
    DetalleVenta guardarDetalle(DetalleVenta detalle);
}
