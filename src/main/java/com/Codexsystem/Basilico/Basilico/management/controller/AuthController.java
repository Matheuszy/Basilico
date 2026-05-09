package com.Codexsystem.Basilico.Basilico.management.controller;

import com.Codexsystem.Basilico.Basilico.configuration.security.TokenConfig;
import com.Codexsystem.Basilico.Basilico.management.dto.request.LoginRequestDto;
import com.Codexsystem.Basilico.Basilico.management.dto.response.LoginResponseDto;
import com.Codexsystem.Basilico.Basilico.management.model.Usuario;
import com.Codexsystem.Basilico.Basilico.management.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.PasswordAuthentication;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenConfig tokenConfig;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequestDto.username(),
                loginRequestDto.senha()
        );

        Authentication authentication = authenticationManager.authenticate(authToken);

        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        String token = tokenConfig.generateToken(usuarioLogado);



        return new LoginResponseDto(token);
    }

}
