package org.example.projecttwo.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CrearUsuarioDTO {
    /*@NotNull
    private Long idUsuario;*/

    @NotBlank
    private String nombreUsuario;

    @NotBlank
    private String contrasenna;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Boolean activo;

    @NotNull
    private LocalDate fechaCreacion;
}
