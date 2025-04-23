package com.tienda.puntoventa.repository;

import com.tienda.puntoventa.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

