package cl.gvidal.techtest.dto;

import cl.gvidal.techtest.model.Phones;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import java.util.Date;
import java.util.List;

@Getter
@Builder
public class DtoRegistro {
    @NotEmpty(message = "The field name cannot be empty or null.")
    private String username;

    @NotEmpty(message = "The field password cannot be empty or null.")
    private String password;

    @NotEmpty(message = "The field email cannot be empty or null.")
    private String email;

    private String created;
    private String modified;
    private Boolean isactive;
    private Date token;
    private List<Phones> phones;

}
