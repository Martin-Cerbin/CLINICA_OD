package tp.ClinicaOdontologica.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.ClinicaOdontologica.Model.Odontologo;
import tp.ClinicaOdontologica.Service.OdontologoService;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    //asociamos la vista con el DAO
    @Autowired
    private OdontologoService odontologoService= new OdontologoService();
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(odontologoService.buscarOdontologo(id));
    }


}
