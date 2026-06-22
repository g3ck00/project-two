package org.example.projecttwo.service;

//import org.springdoc.core.converters.models.Pageable;
import org.example.projecttwo.dto.ActualizarUsuarioDTO;
import org.example.projecttwo.dto.CrearUsuarioDTO;
import org.example.projecttwo.dto.LeerUsuariosDetalladosDTO;
import org.example.projecttwo.dto.UsuarioDTO;
import org.example.projecttwo.entity.Usuario;
import org.example.projecttwo.mapper.UsuarioMapper;
import org.example.projecttwo.repository.RolPantallaRepository;
import org.example.projecttwo.repository.UsuarioRepository;
import org.example.projecttwo.repository.UsuarioRolRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolPantallaRepository rolPantallaRepository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            UsuarioRolRepository usuarioRolRepository,
            RolPantallaRepository rolPantallaRepository,
            UsuarioMapper mapper,
            PasswordEncoder passwordEncoder)
    {
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.rolPantallaRepository = rolPantallaRepository;
        this.mapper=mapper;
        this.passwordEncoder = passwordEncoder;
    }

    /*public List<Rol> obtenerRolesPorUsuario(Long idUsuario) {
        return usuarioRolRepository.findByUsuario_IdUsuarioAndActivoTrue(idUsuario)
                .stream()
                .map(UsuarioRol::getRol)
                .collect(Collectors.toList());
    }*/

    //Leer usuarios
    public List<UsuarioDTO> obtenerUsuarios(){
        return usuarioRepository.findAll().stream().map(mapper::toDTO).toList();
    }

    //Leer usuarios (con paginación)
    public Page<Usuario> obtenerUsuariosPaginados(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }

    public Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    //Leer usuarios v2
    public List<LeerUsuariosDetalladosDTO> leerUsuariosDetalladosDTO() {

        List<Usuario> usuarios = usuarioRepository.findAllConRoles();

        return usuarios.stream()
                .map(this::mapToDetalladoDTO)
                .toList();
    }

    private LeerUsuariosDetalladosDTO mapToDetalladoDTO(Usuario u) {

        LeerUsuariosDetalladosDTO dto = new LeerUsuariosDetalladosDTO();

        dto.setIdUsuario(u.getIdUsuario());
        dto.setNombreUsuario(u.getNombreUsuario());
        dto.setEmail(u.getEmail());

        dto.setRoles(
                u.getUsuarioRoles()
                        .stream()
                        .filter(ur -> Boolean.TRUE.equals(ur.getActivo()))
                        .map(ur -> ur.getRol().getNombreRol())
                        .toList()
        );

        return dto;
    }

    //Crear usuario
    public UsuarioDTO crearUsuario(CrearUsuarioDTO dto){
        Usuario usuario = mapper.toEntity(dto);

        usuario.setContrasenna(passwordEncoder.encode(dto.getContrasenna())); //Encriptar la contraseña luego de mapper
        System.out.println(passwordEncoder.encode("Admin123"));

        Usuario guardado = usuarioRepository.save(usuario);

        return mapper.toDTO(guardado);
    }

    //Modificar usuario
    public UsuarioDTO actualizarUsuario(Long id, ActualizarUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setContrasenna(passwordEncoder.encode(dto.getContrasenna()));
        usuario.setEmail(dto.getEmail());
        usuario.setActivo(dto.getActivo());

        Usuario actualizado = usuarioRepository.save(usuario);

        return mapper.toDTO(actualizado);
    }

    //Query nativos

    public List<Usuario> mostrarTodos(){
        return Collections.singletonList(usuarioRepository.mostrarTodos());
    }
}