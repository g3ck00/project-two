package org.example.projecttwo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class RolPantalla {
    @EmbeddedId
    private RolPantallaId id;

    @ManyToOne
    @MapsId("idRol")
    @JoinColumn(name = "id_rol")
    @JsonIgnore //Parche temporal para evitar lectura paginada bugeada ("n+1" creo que se llama el bug)
    private Rol rol;

    @ManyToOne
    @MapsId("idPantalla")
    @JoinColumn(name = "id_pantalla")
    @JsonIgnore
    private Pantalla pantalla;

    @NotNull
    private Boolean activo;

    @NotNull
    private LocalDate fechaAsignacion;
}
