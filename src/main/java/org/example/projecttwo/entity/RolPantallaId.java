package org.example.projecttwo.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idRol", "idPantalla"})
public class RolPantallaId implements Serializable {

    private Long idRol;
    private Long idPantalla;
}