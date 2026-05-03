package com.Codexsystem.Basilico.Basilico.management.services;

import com.Codexsystem.Basilico.Basilico.management.model.Usuario;
import com.Codexsystem.Basilico.Basilico.management.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findByUsername(String username) {
        if (usuarioRepository.findByUsername(username).isEmpty()) {
            throw new RuntimeException("Usuário não encontrado: " + username);
        }    else {
            return usuarioRepository.findByUsername(username);
        }

    }

    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteByUsername(String username) {
        usuarioRepository.deleteByUsername(username);
    }
}
