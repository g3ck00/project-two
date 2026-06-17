package org.example.projecttwo;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol-asignaciones")
public class UsuarioRolController{
    private final UsuarioRolService usuarioRolService;

    public UsuarioRolController(UsuarioRolService usuarioRolService) {
        this.usuarioRolService = usuarioRolService;
    }

    //Leer roles asignados
    @GetMapping
    public List<UsuarioRolDTO> leerRolesAsignados(){
        return usuarioRolService.leerRolesAsignados();
    }

    //Crear UsuarioRol
    @PostMapping
    public ResponseEntity<String> crearUsuarioRol(@Valid @RequestBody CrearUsuarioRolDTO dto){
        usuarioRolService.crearUsuarioRol(dto);
        return ResponseEntity.ok().body("Asignación de rol completada.");
    }

    //Eliminar rol asignado a usuario
    @DeleteMapping
    public ResponseEntity<String> eliminarRolAsignado(@Valid @RequestBody EliminarUsuarioRolDTO dto){
        usuarioRolService.eliminarUsuarioRol(dto);

        return ResponseEntity.ok("Rol eliminado.");
    }
}
