package tp.ClinicaOdontologica.serviceTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp.ClinicaOdontologica.dto.TurnoDTO;
import tp.ClinicaOdontologica.entity.Domicilio;
import tp.ClinicaOdontologica.entity.Odontologo;
import tp.ClinicaOdontologica.entity.Paciente;
import tp.ClinicaOdontologica.entity.Turno;
import tp.ClinicaOdontologica.service.OdontologoService;
import tp.ClinicaOdontologica.service.PacienteService;
import tp.ClinicaOdontologica.service.TurnoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    TurnoService turnoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarTurno() {
        Paciente paciente = new Paciente("Martin","Cerbin","1234", LocalDate.of(2023,9,7), new Domicilio("calle",1,"Villaguay","Entre Rios"),"email@email.com",null);
        pacienteService.guardarPaciente(paciente);
        Odontologo odontologo = new Odontologo("1234","Juan","Perez",null);
        odontologoService.guardarOdontologo(odontologo);
        Turno turno = new Turno(LocalDate.of(2023,9,25),paciente,odontologo);
        turnoService.guardarTurno(turno);
        assertEquals(1, turno.getId());
    }

    @Test
    @Order(2)
    void buscarPorID() {
        Optional<TurnoDTO> turnobuscado = turnoService.buscarPorID(1L);
        assertEquals(1, turnobuscado.get().getId());
    }

    @Test
    @Order(3)
    void actualizarTurno() {
        Optional<TurnoDTO> turno = turnoService.buscarPorID(1L);

        if (turno.isPresent()) {

            Paciente paciente = new Paciente("Martin", "Cerbin", "1234", LocalDate.of(2023, 9, 7), new Domicilio("calle", 1, "Villaguay", "Entre Rios"), "emailemail@email.com", null);
            pacienteService.guardarPaciente(paciente);
            Odontologo odontologo = new Odontologo("1234", "Juan", "Perez", null);
            odontologoService.guardarOdontologo(odontologo);

            //CREAR NUEVO TURNO CON FECHA DISTINTA
            Turno turnoActualizar = new Turno(1L, LocalDate.of(2023, 9, 20), paciente, odontologo);

            turnoService.actualizarTurno(turnoActualizar);

            assertEquals(LocalDate.of(2023,9,20), turnoActualizar.getFecha());
        }
    }

    @Test
    @Order(4)
    void listarTodos() {
        List<TurnoDTO> listaDeTurnos = turnoService.listarTodos();
        assertEquals(1,listaDeTurnos.size());
    }

    @Test
    @Order(5)
    void eliminarTurno() {
        turnoService.eliminarTurno(1L);
        assertFalse(turnoService.buscarPorID(1L).isPresent());
    }
}