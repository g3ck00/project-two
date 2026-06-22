package org.example.projecttwo.controller;

import lombok.RequiredArgsConstructor;
import org.example.projecttwo.dto.LoginRequest;
import org.example.projecttwo.dto.LoginResponse;
import org.example.projecttwo.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacion")
@RequiredArgsConstructor
public class AutenticacionController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.contrasenna()
        ));

        String token=jwtService.generarToken(request.email());

        return new LoginResponse(token);
    }
}
