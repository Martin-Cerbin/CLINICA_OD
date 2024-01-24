package tp.ClinicaOdontologica.controller;


import org.apache.log4j.Logger;
import tp.ClinicaOdontologica.entity.Odontologo;
import tp.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private static final Logger logger = Logger.getLogger(OdontologoController.class);
    @Autowired
    private OdontologoService odontologoService= new OdontologoService();

    /* ========== BUSCAR POR ID ========== */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Long id){
        logger.info("buscando odontologo por ID");
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if(odontologoBuscado.isPresent()){
            logger.info("Enviando Odontologo buscado");
            return ResponseEntity.ok(odontologoBuscado);
        }else{
            logger.info("No se pudo encontrar el odontologo solicitado");
            return ResponseEntity.badRequest().body(odontologoBuscado);
        }
    }

    /* ========== LISTAR ========== */
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        logger.info("Listando Odontologos");
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    /* ========== AGREGAR ========== */
    @PostMapping
    public ResponseEntity<String> registrarOdontologo(@RequestBody Odontologo odontologo){
        logger.info("Registrando odontologo");
        odontologoService.guardarOdontologo(odontologo);
        return ResponseEntity.ok("Odontologo registrado con exito!");
    }

    /* ========== ELIMINAR ========== */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        logger.info("buscando odontologo a eliminar");
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(id);
        if(odontologoBuscado.isPresent()){
            logger.info("Eliminando Odontologo");
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo eliminado con Exito");
        }else{
            logger.info("No se pudo eliminar el odontologo solicitado");
            return ResponseEntity.badRequest().body("No se pudo eliminar el odontologo solicitado");
        }
    }

    /* ========== ACTUALIZAR ========== */
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        logger.info("buscando odontologo a actualizar");
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(odontologo.getId());
        if(odontologoBuscado.isPresent()){
            logger.info("Actualizando Odontologo");
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo Actualizado con Exito: "+odontologo.getNombre());
        }else{
            logger.info("No se pudo actualizar el odontologo solicitado");
            return ResponseEntity.badRequest().body("No se pudo actualizar el odontologo solicitado: "+odontologo.getNombre());
        }
    }

}
