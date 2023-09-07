package tp.ClinicaOdontologica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp.ClinicaOdontologica.entity.Odontologo;
@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
