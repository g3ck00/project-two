package org.example.projecttwo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarRolDTO {
    @NotBlank(message="El nombre del rol no puede estar vacío...")
    private String nombreRol;

    @NotBlank(message="La descripción del rol no puede estar vacío...")
    private String descripcionRol;
}
