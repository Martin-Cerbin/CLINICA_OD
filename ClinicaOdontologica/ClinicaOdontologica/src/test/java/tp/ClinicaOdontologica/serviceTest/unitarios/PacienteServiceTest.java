package tp.ClinicaOdontologica.serviceTest.unitarios;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp.ClinicaOdontologica.entity.Domicilio;
import tp.ClinicaOdontologica.entity.Paciente;
import tp.ClinicaOdontologica.service.PacienteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    @Test
    @Order(1)
    void guardarPaciente() {
        Paciente paciente = new Paciente("Martin","Cerbin","1234", LocalDate.of(2023,9,7), new Domicilio("calle",1,"Villaguay","Entre Rios"),"email@email.com",null);
        pacienteService.guardarPaciente(paciente);
        assertEquals(1,paciente.getId());
    }

    @Test
    @Order(2)
    void actualizarPaciente() {
        Long idABuscar= 1L;
        Optional<Paciente> paciente = pacienteService.buscarPorId(idABuscar);
        if(paciente.isPresent()) {
            Paciente pacienteAGuardar = new Paciente(idABuscar, "Agustin", "Pereyra", "1234", LocalDate.of(2023, 9, 05), new Domicilio("calle 1", 11, "La Rioja", "La Rioja"), "jorge.pereyra@digitalhouse.com", null);
            pacienteService.actualizarPaciente(pacienteAGuardar);
            Optional<Paciente> pacienteActualizado = pacienteService.buscarPorId(1L);
            assertEquals("Agustin", pacienteActualizado.get().getNombre());
        }
    }

    @Test
    @Order(3)
    void listarTodos() {
        List<Paciente> listaPaciente = pacienteService.listarTodos();
        assertEquals(1,listaPaciente.size());
    }

    @Test
    @Order(4)
    void buscarPorId() {
        Long idAbuscar= 1L;
        Optional<Paciente> pacienteABuscado= pacienteService.buscarPorId(idAbuscar);
        assertNotNull(pacienteABuscado);
    }

    @Test
    @Order(5)
    void eliminarPaciente() {
        Long idEliminar= 1L;
        pacienteService.eliminarPaciente(idEliminar);
        Optional<Paciente> pacienteEliminado= pacienteService.buscarPorId(idEliminar);
        assertFalse(pacienteEliminado.isPresent());
    }
}