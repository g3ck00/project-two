package org.example.projecttwo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(CrearUsuarioDTO dto);
}
