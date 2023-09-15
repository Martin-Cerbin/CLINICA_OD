package tp.ClinicaOdontologica.repository;

import org.springframework.stereotype.Repository;
import tp.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import tp.ClinicaOdontologica.entity.Usuario;

import java.util.Optional;
@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {

}
