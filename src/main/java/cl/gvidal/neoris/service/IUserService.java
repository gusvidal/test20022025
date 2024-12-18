package cl.gvidal.neoris.service;

import cl.gvidal.neoris.dto.DtoAuthRespuesta;
import cl.gvidal.neoris.dto.DtoLogin;
import cl.gvidal.neoris.dto.DtoRegistro;
import cl.gvidal.neoris.dto.RegistroResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<List<RegistroResponse>> listar();
    DtoAuthRespuesta login(DtoLogin dtoLogin);
    ResponseEntity<?> save(DtoRegistro register);
    void deleteById(Long id);
}
