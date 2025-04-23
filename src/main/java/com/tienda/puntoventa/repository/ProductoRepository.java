package com.tienda.puntoventa.repository;

import com.tienda.puntoventa.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
