package org.example.projecttwo;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"rolId", "pantallaId"})
public class RolPantallaId implements Serializable {

    private Long rolId;
    private Long pantallaId;
}