package com.Codexsystem.Basilico.Basilico.management.services;

import com.Codexsystem.Basilico.Basilico.management.model.Usuario;
import com.Codexsystem.Basilico.Basilico.management.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findByUsername(String username) {
        if (usuarioRepository.findByUsername(username).isEmpty()) {
            throw new RuntimeException("Usuário não encontrado: " + username);
        }    else {
            return usuarioRepository.findByUsername(username);
        }

    }

    @Transactional
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleteByUsername(String username) {
        usuarioRepository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User.withUsername(username).password("").roles("USER").build();
    }
}
