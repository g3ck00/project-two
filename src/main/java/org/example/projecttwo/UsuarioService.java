package org.example.projecttwo;

//import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private static UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolPantallaRepository rolPantallaRepository;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            UsuarioRolRepository usuarioRolRepository,
            RolPantallaRepository rolPantallaRepository
    )
    {
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.rolPantallaRepository = rolPantallaRepository;
    }

    /*public List<Rol> obtenerRolesPorUsuario(Long idUsuario) {
        return usuarioRolRepository.findByUsuario_IdUsuarioAndActivoTrue(idUsuario)
                .stream()
                .map(UsuarioRol::getRol)
                .collect(Collectors.toList());
    }*/

    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    public Page<Usuario> obtenerUsuariosPaginados(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }

    public Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    //Query nativos

    public List<Usuario> mostrarTodos(){
        return Collections.singletonList(usuarioRepository.mostrarTodos());
    }
}