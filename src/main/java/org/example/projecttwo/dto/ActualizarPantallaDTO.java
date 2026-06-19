package org.example.projecttwo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarPantallaDTO {
    @NotBlank(message="El nombre de la pantalla no puede estar vacío...")
    private String nombrePantalla;

    @NotBlank(message = "La descripción de la pantalla no puede estar vacía...")
    private String descripcionPantalla;
}
