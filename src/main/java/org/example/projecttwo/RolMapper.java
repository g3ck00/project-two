package org.example.projecttwo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolDTO toDTO(Rol rol);

    //Rol toEntity(CrearRolDTO dto);
}

