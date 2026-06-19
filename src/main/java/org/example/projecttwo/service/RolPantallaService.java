package org.example.projecttwo.service;

import org.example.projecttwo.entity.RolPantallaId;
import org.example.projecttwo.dto.CrearRolPantallaDTO;
import org.example.projecttwo.dto.RolPantallaDTO;
import org.example.projecttwo.entity.Pantalla;
import org.example.projecttwo.entity.Rol;
import org.example.projecttwo.entity.RolPantalla;
import org.example.projecttwo.mapper.RolPantallaMapper;
import org.example.projecttwo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolPantallaService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolPantallaRepository rolPantallaRepository;
    private final RolPantallaMapper rolPantallaMapper;
    private final RolRepository rolRepository;
    private final PantallaRepository pantallaRepository;

    public RolPantallaService(
            UsuarioRepository usuarioRepository,
            UsuarioRolRepository usuarioRolRepository,
            RolPantallaRepository rolPantallaRepository,
            RolPantallaMapper rolPantallaMapper,
            RolRepository rolRepository,
            PantallaRepository pantallaRepository
    ){
        this.usuarioRepository=usuarioRepository;
        this.usuarioRolRepository=usuarioRolRepository;
        this.rolPantallaRepository=rolPantallaRepository;
        this.rolPantallaMapper=rolPantallaMapper;
        this.rolRepository=rolRepository;
        this.pantallaRepository=pantallaRepository;
    }

    //Listar pantallas asignadas
    public List<RolPantallaDTO> leerPantallasAsignadas(){
        return rolPantallaRepository
                .findAll()
                .stream().map(rolPantallaMapper::toDTO)
                .toList();
    }

    //Crear asignación de pantalla para rol
    public RolPantallaDTO crearRolPantalla(CrearRolPantallaDTO dto){
        Rol rol=rolRepository.findById(dto.getIdRol()).orElseThrow(()->new RuntimeException("Rol no encontrado..."));

        Pantalla pantalla=pantallaRepository.findById(dto.getIdPantalla()).orElseThrow(()->new RuntimeException("Pantalla no encontrada..."));

        RolPantalla rolPantalla=new RolPantalla();

        RolPantallaId id=new RolPantallaId();
        id.setIdRol(rol.getIdRol());
        id.setIdPantalla(pantalla.getIdPantalla());

        rolPantalla.setId(id);
        rolPantalla.setRol(rol);
        rolPantalla.setPantalla(pantalla);

        RolPantalla guardado=rolPantallaRepository.save(rolPantalla);

        return rolPantallaMapper.toDTO(guardado);
    }

}
