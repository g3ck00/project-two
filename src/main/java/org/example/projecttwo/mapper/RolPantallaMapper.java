package org.example.projecttwo.mapper;

import org.example.projecttwo.entity.RolPantalla;
import org.example.projecttwo.dto.RolPantallaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface RolPantallaMapper {
    @Mapping(source="rol.idRol",target="idRol")
    @Mapping(source="pantalla.idPantalla",target="idPantalla")
    RolPantallaDTO toDTO(RolPantalla rolPantalla);
}
