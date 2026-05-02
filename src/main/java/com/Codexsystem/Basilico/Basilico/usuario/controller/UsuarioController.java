package com.Codexsystem.Basilico.Basilico.usuario.controller;


import com.Codexsystem.Basilico.Basilico.usuario.dto.UsuarioRequestDto;
import com.Codexsystem.Basilico.Basilico.usuario.dto.UsuarioResponseDto;
import com.Codexsystem.Basilico.Basilico.usuario.model.Usuario;
import com.Codexsystem.Basilico.Basilico.usuario.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public UsuarioResponseDto findByUsername(@RequestParam("username") String username) {
        Optional<Usuario> usuarioOpt = usuarioService.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return new UsuarioResponseDto(usuario.getUsername(), usuario.getEmail());
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    @PostMapping("/createuser")
    public Usuario createUser(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario(usuarioRequestDto.username(),
                usuarioRequestDto.email(),
                usuarioRequestDto.password());

        usuarioService.create(usuario);
        return ResponseEntity.status(201).body(usuario).getBody();
    }

    @DeleteMapping("/deleteuser")
    public void deleteUser(@RequestParam("username") String username) {
        usuarioService.deleteByUsername(username);
    }


}
