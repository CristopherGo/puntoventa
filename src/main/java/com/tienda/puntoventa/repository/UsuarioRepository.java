package com.tienda.puntoventa.repository;

import com.tienda.puntoventa.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username); // útil para login
}

