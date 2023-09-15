package tp.ClinicaOdontologica.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tp.ClinicaOdontologica.entity.*;
import tp.ClinicaOdontologica.repository.OdontologoRepository;
import tp.ClinicaOdontologica.repository.PacienteRepository;
import tp.ClinicaOdontologica.repository.TurnoRepository;
import tp.ClinicaOdontologica.repository.UsuarioRepository;

import java.time.LocalDate;

@Component
public class CargadorInicial implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private TurnoRepository turnoRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //crear un usuario
        //guardarlo en bdd
        //necesito la password
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();

        String claveAdmin= cifrador.encode("admin");
        Usuario usuarioAdmin= new Usuario("Martin","martincito","martin@email.com",claveAdmin, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);

        String claveUser= cifrador.encode("user");
        Usuario usuarioUser= new Usuario("Juan","Juancito","juan@email.com",claveUser, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioUser);
    }
}
