package org.example.projecttwo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CrearUsuarioRolDTO {
    @NotNull(message="La ID del usuario no puede estar vacía...")
    private Long idUsuario;

    @NotNull(message="La ID del rol no puede estar vacía...")
    private Long idRol;

    @NotNull(message="La fecha de asignación no puede estar vacía...")
    private LocalDate fechaAsignacion;

    @NotNull(message="El estado del usuario no puede estar vacío (true/false)...")
    private Boolean activo;
}
