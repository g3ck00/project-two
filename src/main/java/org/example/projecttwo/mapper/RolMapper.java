package org.example.projecttwo.mapper;

import org.example.projecttwo.entity.Rol;
import org.example.projecttwo.dto.CrearRolDTO;
import org.example.projecttwo.dto.RolDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolDTO toDTO(Rol rol);

    Rol toEntity(CrearRolDTO dto);
}