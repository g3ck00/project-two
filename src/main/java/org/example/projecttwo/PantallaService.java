package org.example.projecttwo;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PantallaService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolPantallaRepository rolPantallaRepository;
    private final PantallaMapper pantallaMapper;
    private final PantallaRepository pantallaRepository;

    public PantallaService(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            UsuarioRolRepository usuarioRolRepository,
            RolPantallaRepository rolPantallaRepository,
            PantallaMapper pantallaMapper,
            PantallaRepository pantallaRepository
    )
    {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.rolPantallaRepository = rolPantallaRepository;
        this.pantallaMapper = pantallaMapper;
        this.pantallaRepository = pantallaRepository;
    }

    //Leer registros de pantallas
    public List<PantallaDTO> leerPantallas(){
        return pantallaRepository
                .findAll()
                .stream()
                .map(pantallaMapper::toDTO)
                .toList();
    }

    //Crear registro de pantalla
    public PantallaDTO crearPantalla(CrearPantallaDTO dto){
        Pantalla pantalla=pantallaMapper.toEntity(dto);

        Pantalla guardado=pantallaRepository.save(pantalla);

        return pantallaMapper.toDTO(guardado);
    }

    //Actualizar registro de pantalla
    public PantallaDTO actualizarPantalla(Long id, ActualizarPantallaDTO dto){
        Pantalla pantalla=pantallaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pantalla no encontrada"));

        pantalla.setNombrePantalla(dto.getNombrePantalla());
        pantalla.setDescripcionPantalla(dto.getDescripcionPantalla());

        Pantalla actualizado=pantallaRepository.save(pantalla);

        return pantallaMapper.toDTO(actualizado);
    }

    public void eliminarPantalla(Long id){
        if (!pantallaRepository.existsById(id)){
            throw new ResourceNotFoundException("Pantalla no encontrada...");
        }
        pantallaRepository.deleteById(id);
    }
}
