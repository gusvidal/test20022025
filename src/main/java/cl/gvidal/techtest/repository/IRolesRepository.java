package cl.gvidal.techtest.repository;

import cl.gvidal.techtest.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String roleName);
}
