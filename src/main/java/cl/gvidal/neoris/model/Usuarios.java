package cl.gvidal.neoris.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    private String username;
    private String password;
    private String email;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private Boolean isactive;

    // EAGER: Cuando consultamos por un usuario, se trae todos los roles que tiene
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_roles",
            joinColumns = @JoinColumn(
                    name = "usuario_id", referencedColumnName = "id_usuario"
            ),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role")
    )
    private List<Roles> roles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    private List<Phones> phones = new ArrayList<>();
}
