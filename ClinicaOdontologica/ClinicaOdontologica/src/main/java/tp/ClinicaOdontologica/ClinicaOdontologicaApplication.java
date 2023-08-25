package tp.ClinicaOdontologica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tp.ClinicaOdontologica.Repository.BD;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);

		BD.getConnection();
		BD.crearTablas();

	}


}
