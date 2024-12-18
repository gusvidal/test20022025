package cl.gvidal.neoris.repository;

import cl.gvidal.neoris.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String roleName);
}
