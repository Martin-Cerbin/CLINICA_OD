package tp.ClinicaOdontologica.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tp.ClinicaOdontologica.Model.Paciente;
import tp.ClinicaOdontologica.Service.PacienteService;

import java.util.List;
import java.util.logging.Logger;

@RestController  //ahora trabajo con vista, no va RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService= new PacienteService();

    // ========== CREAR ========== //

    @PostMapping //<<-- me va a permitir crear un nuevo paciente
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    // ========== ACTUALIZAR ========== //

    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){
       Paciente pacienteBuscado= pacienteService.buscarPorID(paciente.getId());
       if(pacienteBuscado!=null){
           pacienteService.actualizarPaciente(paciente);
           return "Paciente Actualizado con Exito: "+paciente.getNombre();
       }else{
           return "No se pudo actualizar el paciente solicitado: "+paciente.getNombre();
       }
    }

    // ========== BUSCAR POR ID ========== //

    @GetMapping("/{id}")
    public Paciente buscarPacienteID (@PathVariable("id") Integer id){
        return pacienteService.buscarPorID(id);
    }

    // ========== BORRAR POR ID ========== //

    @DeleteMapping("/{id}")
    public void borrarPaciente(@PathVariable("id") Integer id){
        pacienteService.eliminarPaciente(id);
    }

    // ========== BUSCAR TODOS ========== //

    @GetMapping("listar")
    public List<Paciente> listarPacientes(){
        return pacienteService.obtenerPacientes();
    }



/*
   @GetMapping
    public String buscarPorCorreo(Model model, @RequestParam("email") String correo){
        //busqueda la tiene en el paciente
        Paciente paciente= pacienteService.buscarPorEmail(correo);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        //estos resultados se los debo pasar a la vista
        return "index";
    }
*/
}
