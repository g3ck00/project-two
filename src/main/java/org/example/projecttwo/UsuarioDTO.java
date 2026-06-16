package org.example.projecttwo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String nombreUsuario;
    private String email;
    private Boolean activo;
    private LocalDate fechaCreacion;
}
