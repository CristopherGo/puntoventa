package com.tienda.puntoventa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data //generate getters and setters with lombok
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}

