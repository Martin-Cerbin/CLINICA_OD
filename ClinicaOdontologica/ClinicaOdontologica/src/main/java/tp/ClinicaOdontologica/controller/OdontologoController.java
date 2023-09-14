package tp.ClinicaOdontologica.controller;

import tp.ClinicaOdontologica.entity.Odontologo;
import tp.ClinicaOdontologica.entity.Paciente;
import tp.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    //asociamos la vista con el DAO
    @Autowired
    private OdontologoService odontologoService= new OdontologoService();

    /* ========== BUSCAR POR ID ========== */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    /* ========== LISTAR ========== */
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    /* ========== AGREGAR ========== */
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    /* ========== ELIMINAR ========== */
    @DeleteMapping
    public void eliminarOdontologo(Long id){
        odontologoService.eliminarOdontologo(id);
    }

    /* ========== ACTUALIZAR ========== */
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(odontologo.getId());
        if(odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Paciente Actualizado con Exito: "+odontologo.getNombre());
        }else{
            return ResponseEntity.badRequest().body("No se pudo actualizar el paciente solicitado: "+odontologo.getNombre());
        }
    }

}
