package org.example.projecttwo.mapper;

import org.example.projecttwo.entity.Usuario;
import org.example.projecttwo.dto.CrearUsuarioDTO;
import org.example.projecttwo.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(CrearUsuarioDTO dto);
}
