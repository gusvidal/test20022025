package cl.gvidal.neoris.repository;

import cl.gvidal.neoris.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuarios, Long> {
    Usuarios findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
