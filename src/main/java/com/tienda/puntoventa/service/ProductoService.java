package com.tienda.puntoventa.service;

import com.tienda.puntoventa.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listarProductos();
    Producto guardarProducto(Producto producto);
    Producto obtenerProductoPorId(Long id);
    void eliminarProducto(Long id);
}
