package org.example.projecttwo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ActualizarUsuarioDTO {
    @NotBlank
    private String nombreUsuario;

    @NotBlank
    private String contrasenna;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Boolean activo;
}
