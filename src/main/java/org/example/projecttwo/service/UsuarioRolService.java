package org.example.projecttwo.service;

import org.example.projecttwo.entity.UsuarioRolId;
import org.example.projecttwo.dto.CrearUsuarioRolDTO;
import org.example.projecttwo.dto.EliminarUsuarioRolDTO;
import org.example.projecttwo.dto.UsuarioRolDTO;
import org.example.projecttwo.entity.Rol;
import org.example.projecttwo.entity.Usuario;
import org.example.projecttwo.entity.UsuarioRol;
import org.example.projecttwo.mapper.UsuarioRolMapper;
import org.example.projecttwo.repository.RolPantallaRepository;
import org.example.projecttwo.repository.RolRepository;
import org.example.projecttwo.repository.UsuarioRepository;
import org.example.projecttwo.repository.UsuarioRolRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
public class UsuarioRolService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolPantallaRepository rolPantallaRepository;
    private final UsuarioRolMapper usuarioRolMapper;
    private final RolRepository rolRepository;

    public UsuarioRolService(
            UsuarioRepository usuarioRepository,
            UsuarioRolRepository usuarioRolRepository,
            RolPantallaRepository rolPantallaRepository,
            UsuarioRolMapper usuarioRolMapper,
            RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.rolPantallaRepository = rolPantallaRepository;
        this.usuarioRolMapper = usuarioRolMapper;
        this.rolRepository = rolRepository;
    }

    //Leer roles asignados
    public List<UsuarioRolDTO> leerRolesAsignados(){
        return usuarioRolRepository.findAll().stream().map(usuarioRolMapper::toDTO).toList();
    }

    //Crear UsuarioRol
    public UsuarioRolDTO crearUsuarioRol(CrearUsuarioRolDTO dto){

        Usuario usuario=usuarioRepository.findById(dto.getIdUsuario()).orElseThrow(()->new RuntimeException("Usuario no encontrado..."));

        Rol rol = rolRepository.findById(dto.getIdRol()).orElseThrow(()->new RuntimeException("Rol no encontrado..."));

        UsuarioRol usuarioRol=new UsuarioRol();

        UsuarioRolId id=new UsuarioRolId();
        id.setUsuarioId(usuario.getIdUsuario());
        id.setRolId(rol.getIdRol());

        usuarioRol.setId(id);
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        usuarioRol.setFechaAsignacion(dto.getFechaAsignacion());
        usuarioRol.setActivo(dto.getActivo());

        UsuarioRol guardado=usuarioRolRepository.save(usuarioRol);

        return usuarioRolMapper.toDTO(guardado);

        /*UsuarioRol usuarioRol = usuarioRolMapper.toEntity(dto);

        UsuarioRol guardado=usuarioRolRepository.save(usuarioRol);

        return  usuarioRolMapper.toDTO(guardado);*/
    }

    //Eliminar rol asignado a usuario
    @DeleteMapping
    public UsuarioRolDTO eliminarUsuarioRol(EliminarUsuarioRolDTO dto){
        UsuarioRolId id=new UsuarioRolId();

        id.setUsuarioId(dto.getIdUsuario());
        id.setRolId(dto.getIdRol());

        if (!usuarioRolRepository.existsById(id)){
            throw new RuntimeException(("El usuario no tiene ese rol..."));
        }

        usuarioRolRepository.deleteById(id);
        return null;
    }
}
