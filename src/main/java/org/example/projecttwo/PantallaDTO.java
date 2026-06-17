package org.example.projecttwo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PantallaDTO {
    private Long idPantalla;

    private String nombrePantalla;

    private String descripcionPantalla;
}
