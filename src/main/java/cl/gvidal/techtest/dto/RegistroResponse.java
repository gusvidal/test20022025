package cl.gvidal.techtest.dto;

import cl.gvidal.techtest.model.Phones;
import cl.gvidal.techtest.model.Roles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistroResponse {
    private Long idUsuario;
    private String username;
    private String password;
    private String email;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private Boolean isactive;
    private List<Roles> roles;
    private List<Phones> phones;
}
