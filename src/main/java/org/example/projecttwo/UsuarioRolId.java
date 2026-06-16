package org.example.projecttwo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class UsuarioRolId implements java.io.Serializable {
    private Long usuarioId;
    private Long rolId;

    public UsuarioRolId(){}

    public UsuarioRolId(Long usuarioId, Long rolId){
        this.usuarioId=usuarioId;
        this.rolId=rolId;
    }
}
