package org.example.projecttwo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.projecttwo.service.CustomUserDetailsService;
import org.example.projecttwo.service.JwtService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException{

        String authHeader=request.getHeader("Authorization");

        ///
        System.out.println("Authorization: " + authHeader);
        ///

        if (authHeader==null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token=authHeader.substring(7); //?
        String nombreUsuario=jwtService.extraerNombreUsuario(token);

        ///
        System.out.println("Username: " + nombreUsuario);
        ///

        if (nombreUsuario!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails=customUserDetailsService.loadUserByUsername(nombreUsuario);

            UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);

            ///
            System.out.println("Usuario autenticado");
            ///
        }

        filterChain.doFilter(request,response);
    }
}