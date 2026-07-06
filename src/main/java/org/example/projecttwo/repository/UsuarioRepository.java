package org.example.projecttwo.repository;

import org.example.projecttwo.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByActivoTrue();

    /*
    public List<Usuario> mostrarTodos(){
        return usuarioRepository.mostrarTodos();
    */

    // #################### JPQL ####################

    @Query("""
            SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.usuarioRoles ur LEFT JOIN FETCH ur.rol
    WHERE u.activo = true
    """)
    List<Usuario> findAllConRoles();

    //Read todos los usuarios
    @Query("""
        select u 
        from Usuario u
        """)
    List<Usuario> readUsuariosJPQL();

    //Read todos los usuarios (JPQL, detallados)
    @Query("""
        select u 
        from Usuario u
        """)
    Page<Usuario> readUsuariosJPQLDetallados(Pageable pageable);

    //Read todos los usuarios where estado del registro = false
    @Query("""
        select u 
        from Usuario u
        where u.activo=false
        """)
    Page<Usuario> readUsuariosJPQLConEstadoDelRegistroInactivo(Pageable pageable);

    //Read todos los usuarios where ID>=10 and ID<=20
    @Query("""
        select u 
        from Usuario u
        where u.idUsuario>=10
        and u.idUsuario<=20
        """)
    Page<Usuario> readUsuariosJPQLConIDEntreDiezYVeinteInclusivos(Pageable pageable);

    //Read todos los usuarios where activo...?
    @Query("""
        select u
        from Usuario u
        where u.activo=:parametroOpcional
        or u.activo is null
        """)
    Page<Usuario> readUsuariosActivosOInactivos(Pageable pageable);

    //Query nativos

    /*
    @Query(value="SELECT * FROM usuario", nativeQuery=true) List<Usuario> mostrarTodos();
    */
}