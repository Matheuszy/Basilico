package com.Codexsystem.Basilico.Basilico.management.controller;


import com.Codexsystem.Basilico.Basilico.management.dto.RegisterRequestDto;
import com.Codexsystem.Basilico.Basilico.management.dto.RegisterResponseDto;
import com.Codexsystem.Basilico.Basilico.management.model.Usuario;
import com.Codexsystem.Basilico.Basilico.management.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public RegisterResponseDto createUser(@RequestBody @Valid RegisterRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario(usuarioRequestDto.username(),
                usuarioRequestDto.email(),
                bCryptPasswordEncoder.encode(usuarioRequestDto.password()));

        usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new RegisterResponseDto(usuario.getUsername(), usuario.getEmail())).getBody();
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam("username") String username) {
        usuarioService.deleteByUsername(username);
    }


}
