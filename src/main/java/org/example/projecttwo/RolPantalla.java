package org.example.projecttwo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolPantalla {
    @EmbeddedId
    private RolPantallaId id;

    @ManyToOne
    @MapsId("rolId")
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @ManyToOne
    @MapsId("pantallaId")
    @JoinColumn(name = "id_pantalla")
    private Pantalla pantalla;

    @NotNull
    private Boolean activo;

    @NotNull
    private LocalDate fechaAsignacion;
}
