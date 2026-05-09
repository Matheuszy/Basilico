package com.Codexsystem.Basilico.Basilico.management.controller;


import com.Codexsystem.Basilico.Basilico.management.dto.RegisterRequestDto;
import com.Codexsystem.Basilico.Basilico.management.dto.RegisterResponseDto;
import com.Codexsystem.Basilico.Basilico.management.model.Usuario;
import com.Codexsystem.Basilico.Basilico.management.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public RegisterResponseDto findByUsername(@RequestParam("username") String username) {
        Optional<Usuario> usuarioOpt = usuarioService.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return new RegisterResponseDto(usuario.getUsername(), usuario.getEmail());
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    @PostMapping("/create")
    public Usuario createUser(@RequestBody @Valid RegisterRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario(usuarioRequestDto.username(),
                usuarioRequestDto.email(),
                usuarioRequestDto.password());

        usuarioService.create(usuario);
        return ResponseEntity.status(201).body(usuario).getBody();
    }

    @DeleteMapping("/deleteu")
    public void deleteUser(@RequestParam("username") String username) {
        usuarioService.deleteByUsername(username);
    }


}
