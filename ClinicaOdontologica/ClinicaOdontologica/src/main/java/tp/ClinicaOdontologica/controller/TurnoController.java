package tp.ClinicaOdontologica.controller;

import tp.ClinicaOdontologica.dto.TurnoDTO;
import tp.ClinicaOdontologica.entity.Odontologo;
import tp.ClinicaOdontologica.entity.Paciente;
import tp.ClinicaOdontologica.entity.Turno;
import tp.ClinicaOdontologica.service.OdontologoService;
import tp.ClinicaOdontologica.service.PacienteService;
import tp.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService = new TurnoService();
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    /* ========== BUSCAR TODOS ========== */
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    /* ========== AGREGAR ========== */
    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        //aca tengo el primer filtro
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            //ambos existen
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /* ========== ELIMINAR ========== */
    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorID(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno Eliminado con Exito: ");
        } else {
            return ResponseEntity.badRequest().body("No se pudo eliminar el turno solicitado: ");
        }
    }

    /* ========== BUSCAR POR ID ========== */
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<TurnoDTO>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(turnoService.buscarPorID(id));
    }

    /* ========== ACTUALIZAR ========== */
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turnoDTO) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorID(turnoDTO.getId());
        if (turnoBuscado.isPresent()) {
            Turno turno = turnoService.turnoDTOaTurno(turnoDTO);
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno Actualizado con Exito: " + turnoDTO.getId());
        } else {
            return ResponseEntity.badRequest().body("No se pudo actualizar el turno solicitado: " + turnoDTO.getId());
        }
    }
}
