package cl.gvidal.neoris.service;

import cl.gvidal.neoris.dto.DtoAuthRespuesta;
import cl.gvidal.neoris.dto.DtoLogin;
import cl.gvidal.neoris.dto.DtoRegistro;
import cl.gvidal.neoris.dto.RegistroResponse;
import cl.gvidal.neoris.exceptions.UsuarioNotFoundException;
import cl.gvidal.neoris.mapper.Mapper;
import cl.gvidal.neoris.mapper.UsuarioMapper;
import cl.gvidal.neoris.model.Roles;
import cl.gvidal.neoris.model.Usuarios;
import cl.gvidal.neoris.repository.IRolesRepository;
import cl.gvidal.neoris.repository.IUsuarioRepository;
import cl.gvidal.neoris.security.JwtTokenProvider;
import cl.gvidal.neoris.utils.EmailValidator;
import cl.gvidal.neoris.utils.PassValidator;
import cl.gvidal.neoris.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static cl.gvidal.neoris.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRolesRepository rolesRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioMapper usuarioMapper;

    @Value("${regex.email}")
    private String regexEmail;

    @Value("${regex.password}")
    private String regexPassword;


    @Override
    public ResponseEntity<List<RegistroResponse>> listar() throws UsuarioNotFoundException{
            // Si la lista està vacìa, no hay usuarios creados, y por lo tanto no hay token
            List<RegistroResponse> response = usuarioMapper.toRegistroResponseList(usuarioRepository.findAll());
            return new ResponseEntity<List<RegistroResponse>>(response, HttpStatus.OK);
    }

    @Override
    public DtoAuthRespuesta login(DtoLogin dtoLogin) {
        DtoAuthRespuesta dtoAuthRespuesta = new DtoAuthRespuesta();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    dtoLogin.getUsername(), dtoLogin.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generarToken(authentication);
            Usuarios userUpdate = new Usuarios();
            userUpdate = usuarioRepository.findByUsername(dtoLogin.getUsername());
            userUpdate.setToken(token);
            userUpdate.setLastLogin(String.valueOf(LocalDateTime.now()));
            usuarioRepository.save(userUpdate);

            Usuarios creado = usuarioRepository.findByUsername(userUpdate.getUsername());
            Mapper myMapper = new Mapper();
            dtoAuthRespuesta = myMapper.userToDto(creado);

        }catch(Exception e){

            return dtoAuthRespuesta;
        }
        return dtoAuthRespuesta;
    }

    @Override
    public ResponseEntity<?> save(DtoRegistro register) {
        Response resp = new Response();

        if(usuarioRepository.existsByUsername(register.getUsername())){
            resp.response = USER_EXISTS;
            return new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
        }
        if(usuarioRepository.existsByEmail(register.getEmail())){
            resp.response = MAIL_EXISTS;
            return new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
        }
        EmailValidator emailValidator = new EmailValidator();
        if (!EmailValidator.validaEmail(register.getEmail(), regexEmail)){
            resp.response = MAIL_INVALID;
            return new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
        }

        PassValidator passValidator = new PassValidator();
        if (!PassValidator.validaPassword(register.getPassword(), regexPassword)){
            resp.response = PASSWORD_INVALID;
            return new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
        }

        Usuarios user = new Usuarios();
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        Roles roles = rolesRepository.findByName(AUTHORITY_ADMIN).get();
        user.setRoles(Collections.singletonList(roles));
        user.setCreated(String.valueOf(LocalDateTime.now()));
        user.setIsactive(Boolean.TRUE);
        user.setEmail(register.getEmail());
        user.setUsername(register.getUsername());
        user.setPhones(register.getPhones());
        try {
            usuarioRepository.save(user);
        }catch(Exception e){
            resp.response = USER_NOT_CREATED + e.getMessage();
            return new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
        }
        resp.response = USER_CREATED_SUCCESFULLY;
        RegistroResponse registroResponse = usuarioMapper.toRegistroResponse(user);

        return new ResponseEntity<RegistroResponse>(registroResponse, HttpStatus.CREATED);
    }

    @Override
    public void deleteById(Long id) {
        if(usuarioRepository.findById(id).isEmpty()){
            throw new UsuarioNotFoundException();
        }
        usuarioRepository.deleteById(id);
    }
}