package tp.ClinicaOdontologica.repository;
import org.springframework.stereotype.Repository;
import tp.ClinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {
}
