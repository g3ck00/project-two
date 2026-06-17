package org.example.projecttwo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class UsuarioRol {
    @EmbeddedId
    private UsuarioRolId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name="id_usuario")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @MapsId("rolId")
    @JoinColumn(name="id_rol")
    private Rol rol;

    @NotNull(message="La fecha de asignación es obligatoria...")
    private LocalDate fechaAsignacion;

    // id de designador

    @NotNull(message="El estado del registro es obligatorio (true/false).")
    private Boolean activo;
}
