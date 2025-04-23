package com.tienda.puntoventa.service;

import com.tienda.puntoventa.model.Usuario;
import com.tienda.puntoventa.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario obtenerPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public boolean existeUsuario(String username) {
        return usuarioRepository.findByUsername(username) != null;
    }

    // Crea un usuario admin si no hay ninguno
    @PostConstruct
    public void crearAdminPorDefecto() {
        if (!existeUsuario("admin")) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // Contraseña temporal
            admin.setRol("ADMIN");
            usuarioRepository.save(admin);
            System.out.println(">>> Usuario admin creado con contraseña: admin123");
        }
    }
}
