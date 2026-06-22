package org.example.projecttwo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @OneToMany(mappedBy="rol")
    @JsonIgnore
    private List<UsuarioRol> usuarioRoles;

    @OneToMany(mappedBy="rol")
    @JsonIgnore
    private List<RolPantalla> rolPantallas;

    @NotBlank
    private String nombreRol;

    @NotBlank
    private String descripcionRol;
}
