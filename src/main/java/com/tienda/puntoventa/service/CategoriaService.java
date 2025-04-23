package com.tienda.puntoventa.service;

import com.tienda.puntoventa.model.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listarCategorias();
    Categoria guardarCategoria(Categoria categoria);
    Categoria obtenerCategoriaPorId(Long id);
    void eliminarCategoria(Long id);
}
