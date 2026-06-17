package org.example.projecttwo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EliminarUsuarioRolDTO {
    @NotNull(message="La ID del usuario no puede estar vacía...")
    private Long idUsuario;

    @NotNull(message="La ID del rol no puede estar vacía...")
    private Long idRol;
}
