package org.example.projecttwo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearPantallaDTO {
    @NotBlank(message ="El nombre de la pantalla no puede estar vacío...")
    private String nombrePantalla;

    @NotBlank(message="La descripción de la pantalla no puede estar vacía...")
    private String descripcionPantalla;
}
