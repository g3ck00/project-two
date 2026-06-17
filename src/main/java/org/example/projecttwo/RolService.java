package org.example.projecttwo;

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

    public List<RolDTO> leerRoles(){
        return rolRepository.findAll().stream().map(rolMapper::toDTO).toList();
    }
}
