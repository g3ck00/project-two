package org.example.projecttwo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pantalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPantalla;

    @OneToMany(mappedBy = "pantalla")
    private List<RolPantalla> rolPantallas;

    @NotBlank(message="El nombre de la pantalla no puede estar vacío...")
    private String nombrePantalla;

    @NotBlank(message="La descripción de la pantalla no puede estar vacío...")
    private String descripcionPantalla;
}