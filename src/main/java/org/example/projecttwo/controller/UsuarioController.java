package org.example.projecttwo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.example.projecttwo.dto.LeerUsuariosDetalladosDTO;
import org.example.projecttwo.service.UsuarioService;
import org.example.projecttwo.dto.ActualizarUsuarioDTO;
import org.example.projecttwo.dto.CrearUsuarioDTO;
import org.example.projecttwo.dto.UsuarioDTO;
import org.example.projecttwo.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /*
    //Leer usuarios v1
    @GetMapping
    public List<UsuarioDTO> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }
    */

    /*
    //Leer usuarios (con paginación)
    @GetMapping("/p")
    public Page<Usuario> obtenerUsuariosPaginados(Pageable pageable){
        return usuarioService.obtenerUsuariosPaginados(pageable);
    }
    */

    //Leer usuarios v2 con paginación
    @GetMapping
    public Page<LeerUsuariosDetalladosDTO> leerUsuariosDetallados(Pageable pageable){
        return usuarioService.leerUsuariosDetallados(pageable);
    }


    //Leer usuarios (Native Query)
    @GetMapping("/jpql/")
    public List<Usuario> readUsuarios() {
        return usuarioService.readUsuarios();
    }

    /*
    //Leer usuarios v2
    @GetMapping()
    public List<LeerUsuariosDetalladosDTO> leerUsuariosDetalladosDTOS(){
        return usuarioService.leerUsuariosDetalladosDTO();
    }
     */

    //Crear usuario
    @PostMapping
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody CrearUsuarioDTO dto){
        usuarioService.crearUsuario(dto);
        return ResponseEntity.ok("Usuario añadido.");
    }

    //Modificar usuario
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody ActualizarUsuarioDTO dto) {
        usuarioService.actualizarUsuario(id, dto);
        return ResponseEntity.ok("Usuario editado.");
    }

    //Leer usuario por ID
    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuario(id);
    }
}