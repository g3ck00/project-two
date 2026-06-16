package org.example.projecttwo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {

    List<UsuarioRol> findByUsuario_IdUsuario(Long idUsuario);

    List<UsuarioRol> findByRol_IdRol(Long idRol);

    List<UsuarioRol> findByUsuario_IdUsuarioAndActivoTrue(Long idUsuario);
}
