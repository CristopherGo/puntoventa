package com.tienda.puntoventa.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Venta venta;

    @ManyToOne
    private Producto producto;

    private Integer cantidad;

    private Double subtotal;
}
