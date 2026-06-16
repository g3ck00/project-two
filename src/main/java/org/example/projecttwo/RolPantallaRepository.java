package org.example.projecttwo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolPantallaRepository extends JpaRepository<RolPantalla, RolPantallaId> {

    List<RolPantalla> findByRol_IdRol(Long idRol);

    List<RolPantalla> findByPantalla_IdPantalla(Long idPantalla);
}