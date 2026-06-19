package org.example.projecttwo.controller;

import jakarta.validation.Valid;
import org.example.projecttwo.service.PantallaService;
import org.example.projecttwo.dto.ActualizarPantallaDTO;
import org.example.projecttwo.dto.CrearPantallaDTO;
import org.example.projecttwo.dto.PantallaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pantallas")
public class PantallaController {
    private final PantallaService pantallaService;

    public PantallaController(PantallaService pantallaService) {
        this.pantallaService = pantallaService;
    }

    //Leer registros de pantallas
    @GetMapping
    public List<PantallaDTO> leerRoles() {
        return pantallaService.leerPantallas();
    }

    //Crear registro de pantalla
    @PostMapping
    public ResponseEntity<String> crearPantalla(@Valid @RequestBody CrearPantallaDTO dto) {
        pantallaService.crearPantalla(dto);
        return ResponseEntity.ok().body("Pantalla creada.");
    }

    //Actualizar registro de pantalla
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPantalla(@PathVariable Long id, @Valid @RequestBody ActualizarPantallaDTO dto) {
        pantallaService.actualizarPantalla(id, dto);
        return ResponseEntity.ok().body("Pantalla actualizada.");
    }

    //Eliminar pantalla (temporal hasta añadir borrado lógico)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPantalla(@PathVariable Long id) {
        pantallaService.eliminarPantalla(id);
        return ResponseEntity.ok().body("Pantalla eliminada.");
    }
}
