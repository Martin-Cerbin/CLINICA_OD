package tp.ClinicaOdontologica.serviceTest.integracion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tp.ClinicaOdontologica.entity.Domicilio;
import tp.ClinicaOdontologica.entity.Odontologo;
import tp.ClinicaOdontologica.entity.Paciente;
import tp.ClinicaOdontologica.service.PacienteService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TestIntegracionPaciente {
    @Autowired
    PacienteService pacienteService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDatos(){
        Paciente paciente = new Paciente("Agustin", "Pereyra", "1234", LocalDate.of(2023, 9, 05), new Domicilio("calle 1", 11, "La Rioja", "La Rioja"), "jorge.pereyra@digitalhouse.com", null);
    }

    @Test
    public void listarPacienteTest() throws Exception {
        cargarDatos();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/pacientes").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }
}
