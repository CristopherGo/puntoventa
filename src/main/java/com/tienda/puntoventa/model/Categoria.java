package com.tienda.puntoventa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data //genera con lombok los getter and setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}

