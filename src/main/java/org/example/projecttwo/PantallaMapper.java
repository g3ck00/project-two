package org.example.projecttwo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PantallaMapper {
    PantallaDTO toDTO(Pantalla pantalla);

    Pantalla toEntity(CrearPantallaDTO dto);
}
