package tp.ClinicaOdontologica.serviceTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp.ClinicaOdontologica.entity.Odontologo;
import tp.ClinicaOdontologica.service.OdontologoService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarOdontologo() {
        Odontologo odontologo = new Odontologo("1234","Juan","Perez",null);
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        assertEquals(1, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    void listarTodos() {
        List<Odontologo> listaOdontologos = odontologoService.listarTodos();
        assertEquals(1, listaOdontologos.size());
    }

    @Test
    @Order(3)
    void buscarPorId() {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(1L);
        assertEquals(1, odontologoBuscado.get().getId());
    }

    @Test
    @Order(4)
    void actualizarOdontologo() {
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(1L);
            assertEquals("1234",odontologo.get().getMatricula());
        if (odontologo.isPresent()) {
            Odontologo odontologoActualizar = new Odontologo(1L,"1111", "Juan", "Perez", null);
            odontologoService.actualizarOdontologo(odontologoActualizar);
            Optional<Odontologo> odontologoActualizado = odontologoService.buscarPorId(1L);
            assertEquals("1111", odontologoActualizado.get().getMatricula());
        }


    }

    @Test
    @Order(5)
    void eliminarOdontologo() {
        odontologoService.eliminarOdontologo(1L);
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarPorId(1L);
        assertFalse(odontologoEliminado.isPresent());
    }
}