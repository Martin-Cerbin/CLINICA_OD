package tp.ClinicaOdontologica.serviceTest.integracion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl;
import org.junit.jupiter.api.Order;
import tp.ClinicaOdontologica.dto.TurnoDTO;
import tp.ClinicaOdontologica.entity.Domicilio;
import tp.ClinicaOdontologica.entity.Odontologo;
import tp.ClinicaOdontologica.entity.Paciente;
import tp.ClinicaOdontologica.entity.Turno;
import tp.ClinicaOdontologica.service.OdontologoService;
import tp.ClinicaOdontologica.service.PacienteService;
import tp.ClinicaOdontologica.service.TurnoService;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)//que voy a desactivar la seguridad.
public class TestIntegracionTurnos {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
     private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;
    public void cargarDatos(){
        Paciente paciente= pacienteService.guardarPaciente(new Paciente("Martin","Cerbin","11111", LocalDate.of(2023,9,25),new Domicilio("Siempre viva",742,"Springfield","Entre Rios"),"martincerbin@dj.com"));
        Odontologo odontologo= odontologoService.guardarOdontologo(new Odontologo("MP10","Romina","Mazzuco"));
        Turno turno= new Turno(LocalDate.of(2023,10,23),paciente,odontologo);
        TurnoDTO turnoDTO= turnoService.guardarTurno(turno);

    }
    @Test
    @Order(1)
    public void listarTodosTurnosTest() throws Exception {
        cargarDatos();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(2)
    public void actualizarTurnoTest() throws Exception {
        cargarDatos();
        TurnoDTO turnoDTO = crearTurnoDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/turnos")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                        .content(objectMapper.writeValueAsString(turnoDTO)))
                        .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    private TurnoDTO crearTurnoDTO(){
        TurnoDTO turnoDTO = new TurnoDTO(1L, LocalDate.of(2023,9,20),1L,1L);
        return turnoDTO;
    };
}
