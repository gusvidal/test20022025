package cl.gvidal.techtest.dto;

import lombok.*;

//Devuelve la información con el token y el tipo de token
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoAuthRespuesta {
    private Long id;
    private String created;
    private String modified;
    private String lastLogin;
    private String accessToken;
    private boolean isActive;
}
