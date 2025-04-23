package com.tienda.puntoventa.service;


import com.tienda.puntoventa.model.Usuario;
import com.tienda.puntoventa.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
/* 
    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByUsername(username);
    if (usuario == null) {
        throw new UsernameNotFoundException("Usuario no encontrado");
    }
    return org.springframework.security.core.userdetails.User.builder()
            .username(usuario.getUsername())
            .password(usuario.getPassword())
            .authorities("ROLE_" + usuario.getRol())  // <-- aquí cambias a authorities
            .build();
}*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRol())  // Spring espera: "ADMIN" o "VENDEDOR"
                .build();
    }



}

