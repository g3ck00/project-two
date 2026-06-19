package org.example.projecttwo.controller;

import jakarta.validation.Valid;
import org.example.projecttwo.service.RolService;
import org.example.projecttwo.dto.ActualizarRolDTO;
import org.example.projecttwo.dto.CrearRolDTO;
import org.example.projecttwo.dto.RolDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    //Leer roles
    @GetMapping
    public List<RolDTO> leerRoles(){
        return rolService.leerRoles();
    }

    //Crear rol
    @PostMapping
    public ResponseEntity<String> crearRol(@Valid @RequestBody CrearRolDTO dto){
        rolService.crearRol(dto);
        return ResponseEntity.ok().body("Rol creado.");
    }

    //Actualizar rol
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarRol(@PathVariable Long id, @Valid @RequestBody ActualizarRolDTO dto){
        rolService.actualizarRol(id, dto);
        return ResponseEntity.ok().body("Rol actualizado.");
    }
}
