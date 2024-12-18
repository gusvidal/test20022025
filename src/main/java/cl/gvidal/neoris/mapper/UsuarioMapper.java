package cl.gvidal.neoris.mapper;

import cl.gvidal.neoris.dto.RegistroResponse;
import cl.gvidal.neoris.model.Usuarios;
import org.mapstruct.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target= "idUsuario", ignore = true)
    @Mapping(target= "created", ignore = true)
    @Mapping(target= "modified", ignore = true)
    @Mapping(target= "lastLogin", ignore = true)
    @Mapping(target= "isactive", ignore = true)
    @Mapping(target= "roles", ignore = true)
    RegistroResponse toRegistroResponse(Usuarios usuarios);

    @Mapping(target= "modified", ignore = true)
    @Mapping(target= "roles", ignore = true)
    List<RegistroResponse> toRegistroResponseList(List<Usuarios> uesrList);
}
