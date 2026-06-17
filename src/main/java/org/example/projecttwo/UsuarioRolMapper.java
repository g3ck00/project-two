package org.example.projecttwo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioRolMapper {

    @Mapping(source="usuario.idUsuario",target="usuarioId")
    @Mapping(source="rol.idRol",target="rolId")
    UsuarioRolDTO toDTO(UsuarioRol usuarioRol);

    UsuarioRol toEntity(CrearUsuarioRolDTO dto);
}
