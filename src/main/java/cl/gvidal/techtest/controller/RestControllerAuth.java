package cl.gvidal.techtest.controller;

import cl.gvidal.techtest.dto.DtoAuthRespuesta;
import cl.gvidal.techtest.dto.DtoLogin;
import cl.gvidal.techtest.dto.DtoRegistro;
import cl.gvidal.techtest.repository.IRolesRepository;
import cl.gvidal.techtest.repository.IUsuarioRepository;
import cl.gvidal.techtest.security.JwtTokenProvider;
import cl.gvidal.techtest.service.IUserService;
import cl.gvidal.techtest.utils.Response;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static cl.gvidal.techtest.utils.Constants.INVALID_CREDENTIALS;
import static cl.gvidal.techtest.utils.Constants.LOGIN_ERROR;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
@Tag(name = "Usuarios", description = "Endpoints para la creación y autenticación de usuarios")
public class RestControllerAuth {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IRolesRepository rolesRepository;
    private final IUsuarioRepository usuarioRepository;
    private final JwtTokenProvider jwtTokenProvider; // tambien se llama JwtGenerator

    private final IUserService iUserService;

    @Value("${regex.email}")
    private String regexEmail;

    @Value("${regex.password}")
    private String regexPassword;

    @PostMapping("v1/register")
    public ResponseEntity<?> save(@Valid @RequestBody DtoRegistro dtoRegistro){
        return iUserService.save(dtoRegistro);
    }

    @PostMapping("v1/login")
    public ResponseEntity<?> login(@RequestBody DtoLogin dtoLogin){
        Response resp = new Response();
        DtoAuthRespuesta responseEntity = new DtoAuthRespuesta();
        try {
            responseEntity = iUserService.login(dtoLogin);
            if (responseEntity.getId() == null){
                resp.response = INVALID_CREDENTIALS;

                return new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            resp.response= LOGIN_ERROR + e.getMessage();
            return new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<DtoAuthRespuesta>(responseEntity, HttpStatus.OK);
    }
    @GetMapping("v1/lista")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> getTodos() {
        return iUserService.listar();
    }

    @DeleteMapping("v1/del/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public void deleteById(@PathVariable Long id){
        iUserService.deleteById(id);
    }
}