package org.example.projecttwo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String SECRET="ultra_resistente_clave_maestra_de_256_bits";

    public String generarToken(String nombreUsuario){
        return Jwts.builder()
                .subject(nombreUsuario)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+900000))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    public String extraerNombreUsuario(String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}