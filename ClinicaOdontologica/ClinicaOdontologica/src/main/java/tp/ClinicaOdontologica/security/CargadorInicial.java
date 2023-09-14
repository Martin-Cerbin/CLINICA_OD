package tp.ClinicaOdontologica.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tp.ClinicaOdontologica.entity.Usuario;
import tp.ClinicaOdontologica.entity.UsuarioRole;
import tp.ClinicaOdontologica.repository.UsuarioRepository;

@Component
public class CargadorInicial implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //crear un usuario
        //guardarlo en bdd
        //necesito la password
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String clave= cifrador.encode("digital");
        System.out.println("CLAVE CIFRADA: "+cifrador);
        Usuario usuario1= new Usuario("Martin","martincito","martin@email.com",clave, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario1);
    }
}
