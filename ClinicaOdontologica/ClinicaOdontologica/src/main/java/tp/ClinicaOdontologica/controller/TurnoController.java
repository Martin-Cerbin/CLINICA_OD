package tp.ClinicaOdontologica.controller;

import tp.ClinicaOdontologica.dto.TurnoDTO;
import tp.ClinicaOdontologica.entity.Turno;
import tp.ClinicaOdontologica.service.OdontologoService;
import tp.ClinicaOdontologica.service.PacienteService;
import tp.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService= new TurnoService();

    /* ========== BUSCAR TODOS ========== */
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodos(){
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    /* ========== AGREGAR ========== */
    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno)                      {
        //aca tengo el primer filtro
        OdontologoService odontologoService= new OdontologoService();
        PacienteService pacienteService= new PacienteService();
        if(pacienteService.buscarPorId(turno.getPaciente().getId()).isPresent() && odontologoService.buscarOdontologo(turno.getOdontologo().getId()).isPresent()){
            //ambos existen
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
                    }
        else{
            return ResponseEntity.badRequest().build();
        }

    }
}
