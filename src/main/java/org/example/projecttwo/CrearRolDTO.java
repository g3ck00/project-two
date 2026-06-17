package org.example.projecttwo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearRolDTO {
    @NotBlank(message="El nombre de rol no puede estar vacío...")
    private String nombreRol;

    @NotBlank(message="La descripción del rol no puede estar vacío...")
    private String descripcionRol;
}
