package org.example.projecttwo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
}
