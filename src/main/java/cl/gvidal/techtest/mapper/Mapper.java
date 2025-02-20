package cl.gvidal.techtest.mapper;

import cl.gvidal.techtest.dto.DtoAuthRespuesta;
import cl.gvidal.techtest.model.Usuarios;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Mapper {
    public DtoAuthRespuesta userToDto(Usuarios user){
        DtoAuthRespuesta response = new DtoAuthRespuesta();
        response.setId(user.getIdUsuario());
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setAccessToken(user.getToken());
        response.setActive(user.getIsactive());

        return response;
    }
}
