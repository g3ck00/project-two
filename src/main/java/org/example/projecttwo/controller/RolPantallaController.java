package org.example.projecttwo.controller;

import jakarta.validation.Valid;
import org.example.projecttwo.service.RolPantallaService;
import org.example.projecttwo.dto.CrearRolPantallaDTO;
import org.example.projecttwo.dto.RolPantallaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pantallas-asignadas")
public class RolPantallaController {
    private final RolPantallaService rolPantallaService;

    public RolPantallaController(RolPantallaService rolPantallaService){
        this.rolPantallaService=rolPantallaService;
    }

    //Leer pantallas asignadas
    @GetMapping
    public List<RolPantallaDTO> leerPantallasAsignadas(){
        return rolPantallaService.leerPantallasAsignadas();
    }

    //Crear pantallas asignadas
    @PostMapping
    public ResponseEntity<String> crearPantallaAsignada(@Valid @RequestBody CrearRolPantallaDTO dto){
        rolPantallaService.crearRolPantalla(dto);
        return ResponseEntity.ok().body("Asignación de pantalla completada.");
    }
}
