package org.example.projecttwo.service;

import lombok.RequiredArgsConstructor;
import org.example.projecttwo.entity.Usuario;
import org.example.projecttwo.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        Usuario usuario=usuarioRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado..."));

        return User.builder().username(usuario.getEmail())
                .password(usuario.getContrasenna())
                .authorities(List.of())
                .build();
    }
}
