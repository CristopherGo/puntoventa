package com.tienda.puntoventa.repository;

import com.tienda.puntoventa.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
