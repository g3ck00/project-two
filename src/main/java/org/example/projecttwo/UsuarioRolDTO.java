package org.example.projecttwo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRolDTO {
    private Long usuarioId;

    private Long rolId;

    private LocalDate fechaAsignacion;

    private Boolean activo;
}
