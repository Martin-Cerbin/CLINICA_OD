package tp.ClinicaOdontologica.controller;

import tp.ClinicaOdontologica.entity.Paciente;
import tp.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController  //ahora trabajo con vista, no va RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService= new PacienteService();

    /* ========== AGREGAR ========== */
    @PostMapping //<<-- me va a permitir crear un nuevo paciente
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    /* ========== ACTUALIZAR ========== */
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
       Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(paciente.getId());
       if(pacienteBuscado.isPresent()){
           pacienteService.actualizarPaciente(paciente);
           return ResponseEntity.ok("Paciente Actualizado con Exito: "+paciente.getNombre());
       }else{
           return ResponseEntity.badRequest().body("No se pudo actualizar el paciente solicitado: "+paciente.getNombre());
       }
    }

    /* ========== BUSCAR POR ID ========== */
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
}

    /* ========== BUSCAR POR EMAIL ========== */
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email){
        return ResponseEntity.ok(pacienteService.buscarPorEmail(email));
    }

    /* ========== ELIMINAR ========== */
    @DeleteMapping
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente Eliminado con Exito: ");
        }else{
            return ResponseEntity.badRequest().body("No se pudo eliminar el paciente solicitado: ");
        }
    }

    /* ========== LISTAR ========== */
    @GetMapping
    public ResponseEntity<List<Paciente>> obtenerLista(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }



}
