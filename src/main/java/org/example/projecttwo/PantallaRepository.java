package org.example.projecttwo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PantallaRepository extends JpaRepository<Pantalla, Long> {

    List<Pantalla> findByNombrePantallaContaining(String nombre);
}