package org.example.projecttwo.mapper;

import org.example.projecttwo.entity.Pantalla;
import org.example.projecttwo.dto.CrearPantallaDTO;
import org.example.projecttwo.dto.PantallaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PantallaMapper {
    PantallaDTO toDTO(Pantalla pantalla);

    Pantalla toEntity(CrearPantallaDTO dto);
}
