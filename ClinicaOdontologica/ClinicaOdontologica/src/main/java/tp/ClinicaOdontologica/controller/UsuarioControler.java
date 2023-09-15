package tp.ClinicaOdontologica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.ClinicaOdontologica.entity.Usuario;
import tp.ClinicaOdontologica.service.UsuarioService;

@RestController  //ahora trabajo con vista, no va RestController
@RequestMapping("/usuarios")
public class UsuarioControler {
    @Autowired
    private UsuarioService usuarioService = new UsuarioService();

    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }
}
