package com.tienda.puntoventa.service;

import com.tienda.puntoventa.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuarios();

    Usuario guardarUsuario(Usuario usuario);

    Usuario obtenerUsuarioPorId(Long id);

    Usuario obtenerPorUsername(String username);

    void eliminarUsuario(Long id);
}
