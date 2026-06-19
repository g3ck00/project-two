package org.example.projecttwo.service;

import org.example.projecttwo.dto.ActualizarRolDTO;
import org.example.projecttwo.dto.CrearRolDTO;
import org.example.projecttwo.dto.RolDTO;
import org.example.projecttwo.entity.Rol;
import org.example.projecttwo.mapper.RolMapper;
import org.example.projecttwo.repository.RolPantallaRepository;
import org.example.projecttwo.repository.RolRepository;
import org.example.projecttwo.repository.UsuarioRepository;
import org.example.projecttwo.repository.UsuarioRolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {
    public UsuarioRepository usuarioRepository;
    public RolRepository rolRepository;
    public final UsuarioRolRepository usuarioRolRepository;
    private final RolPantallaRepository rolPantallaRepository;
    private final RolMapper rolMapper;

    public RolService(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            UsuarioRolRepository usuarioRolRepository,
            RolPantallaRepository rolPantallaRepository,
            RolMapper rolMapper
    )
    {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository=rolRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.rolPantallaRepository = rolPantallaRepository;
        this.rolMapper=rolMapper;
    }

    //Leer roles
    public List<RolDTO> leerRoles(){
        return rolRepository
                .findAll()
                .stream()
                .map(rolMapper::toDTO)
                .toList();
    }

    //Crear rol
    public RolDTO crearRol(CrearRolDTO dto){
        Rol rol=rolMapper.toEntity(dto);

        Rol guardado=rolRepository.save(rol);

        return rolMapper.toDTO(guardado);
    }

    //Actualizar rol
    public RolDTO actualizarRol(Long id, ActualizarRolDTO dto){
        Rol rol=rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        rol.setNombreRol(dto.getNombreRol());
        rol.setDescripcionRol(dto.getDescripcionRol());

        Rol actualizado=rolRepository.save(rol);

        return rolMapper.toDTO(actualizado);
    }

}
