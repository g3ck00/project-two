package org.example.projecttwo;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idPantalla", "idPantalla"})
public class RolPantallaId implements Serializable {

    private Long idRol;
    private Long idPantalla;
}