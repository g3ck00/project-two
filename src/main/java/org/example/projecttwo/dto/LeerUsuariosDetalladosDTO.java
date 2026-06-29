package org.example.projecttwo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class LeerUsuariosDetalladosDTO {
    private Long idUsuario;
    private String nombreUsuario;
    private String email;
    private List<String> roles;
    private Boolean activo;
    private String creadoPor;
    private LocalDate fechaCreacionRegistrada;
    private String modificadoPor;
    private LocalDate fechaModificacion;
}
