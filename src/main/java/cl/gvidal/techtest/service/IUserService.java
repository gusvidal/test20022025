package cl.gvidal.techtest.service;

import cl.gvidal.techtest.dto.DtoAuthRespuesta;
import cl.gvidal.techtest.dto.DtoLogin;
import cl.gvidal.techtest.dto.DtoRegistro;
import cl.gvidal.techtest.dto.RegistroResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<List<RegistroResponse>> listar();
    DtoAuthRespuesta login(DtoLogin dtoLogin);
    ResponseEntity<?> save(DtoRegistro register);
    void deleteById(Long id);
}
