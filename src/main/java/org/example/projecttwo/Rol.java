package org.example.projecttwo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Rol {
    @Id
    private Long idRol;

    @OneToMany(mappedBy="rol")
    private List<UsuarioRol> usuarioRoles;

    @NotBlank
    private String nombreRol;

    @NotBlank
    private String descripcionRol;
}
