package org.example.projecttwo.entity;

import java.time.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<UsuarioRol> usuarioRoles;

    @NotBlank(message="El nombre de usuario es obligatorio...")
    private String nombreUsuario;

    @NotBlank(message="La contraseña es obligatoria...")
    private String contrasenna;

    @NotBlank(message="El correo es obligatorio...")
    @Email(message="Ingrese un correo válido...")
    @Column(unique=true)
    private String email;

    @NotNull(message="El estado del registro es obligatorio...")
    private Boolean activo;

    //Antiguo procedimiento: el creador del registro debía insertar manualmente una fecha
    //Se mantiene aquí, aunque como un "parche" para no romper el esquema SQL con alguna modificación; aporta un valor que no se visualiza ni afecta la programación
    //Debería ser removido en actualizaciones futuras
    //@NotNull(message="La fecha de creación es obligatoria...")
    //@PastOrPresent(message="La fecha de creación no puede ser una fecha futura...")
    private LocalDate fechaCreacion;

    private String creadoPor;
    private LocalDate fechaCreacionRegistrada;

    private String modificadoPor;
    private LocalDate fechaModificacion;
}
