package org.example.projecttwo;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Leer usuarios
    @GetMapping
    public List<UsuarioDTO> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    //Leer usuarios (con paginación)
    @GetMapping("/p")
    public Page<Usuario> obtenerUsuariosPaginados(Pageable pageable){
        return usuarioService.obtenerUsuariosPaginados(pageable);
    }

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

    //Leer usuarios (Native Query)
    @GetMapping("/q")
    public List<Usuario> mostrarTodos(){
        return usuarioService.mostrarTodos();
    }

    //Leer usuario por ID
    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuario(id);
    }
}