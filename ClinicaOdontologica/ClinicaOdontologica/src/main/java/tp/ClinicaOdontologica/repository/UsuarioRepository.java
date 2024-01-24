package tp.ClinicaOdontologica.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tp.ClinicaOdontologica.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String correo);
}
