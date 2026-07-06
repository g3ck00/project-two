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

    // #################### JPQL ####################

    //Endpoint global para JPQL
    // ...

    //Read todos los usuarios (JPQL)
    @GetMapping("/jpql")
    public List<Usuario> readUsuariosJPQL() {
        return usuarioService.readUsuariosJPQL();
    }

    //Read todos los usuarios (JPQL, detallados)
    @GetMapping("/jpql/detallados")
    public Page<LeerUsuariosDetalladosDTO> readUsuariosJPQLDetallados(Pageable pageable) {
        return usuarioService.readUsuariosJPQLDetallados(pageable);
    }

    //Read todos los usuarios where estado del registro = false
    @GetMapping("/jpql/usuarios/registros-inactivos")
    public Page<LeerUsuariosDetalladosDTO> readUsuariosJPQLConEstadoDelRegistroInactivo(Pageable pageable) {
        return usuarioService.readUsuariosJPQLConEstadoDelRegistroInactivo(pageable);
    }

    //Read todos los usuarios where ID>=10 and ID<=20
    @GetMapping("/jpql/usuarios/id-entre-10-y-20-inclusivos")
    public Page<LeerUsuariosDetalladosDTO> readUsuariosJPQLConIDEntreDiezYVeinteInclusivos(Pageable pageable) {
        return usuarioService.readUsuariosJPQLConIDEntreDiezYVeinteInclusivos(pageable);
    }

    //
    @GetMapping("/jpql/usuarios/activos-o-inactivos")
    public Page<LeerUsuariosDetalladosDTO> readUsuariosActivosOInactivos(Pageable pageable)
    {
        return usuarioService.readUsuariosJPQLConIDEntreDiezYVeinteInclusivos(pageable);
    }

}