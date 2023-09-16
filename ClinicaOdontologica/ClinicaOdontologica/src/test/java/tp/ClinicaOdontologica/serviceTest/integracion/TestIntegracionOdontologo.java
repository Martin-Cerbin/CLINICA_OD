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
import tp.ClinicaOdontologica.entity.Odontologo;
import tp.ClinicaOdontologica.service.OdontologoService;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TestIntegracionOdontologo {

    @Autowired
    OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDatos(){
        Odontologo odontologo= odontologoService.guardarOdontologo(new Odontologo("M001","Martin","Cerbin"));
    }

    @Test
    public void listarOdontologosTest() throws Exception {
        cargarDatos();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/odontologos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }
}
