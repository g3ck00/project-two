package org.example.projecttwo;

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

    @NotBlank
    private String nombrePantalla;

    @NotBlank
    private String descripcionPantalla;

    @OneToMany(mappedBy = "pantalla")
    private List<RolPantalla> rolPantallas;
}