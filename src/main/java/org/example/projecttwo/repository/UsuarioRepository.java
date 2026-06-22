package org.example.projecttwo.repository;

import org.example.projecttwo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByActivoTrue();

    //Query nativos

    @Query(value="SELECT * FROM usuario", nativeQuery=true)
    Usuario mostrarTodos();

    @Query("""
    SELECT DISTINCT u FROM Usuario u
    LEFT JOIN FETCH u.usuarioRoles ur
    LEFT JOIN FETCH ur.rol
    WHERE u.activo = true
    """)
    List<Usuario> findAllConRoles();
}