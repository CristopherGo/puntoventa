package com.tienda.puntoventa.repository;

import com.tienda.puntoventa.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}
