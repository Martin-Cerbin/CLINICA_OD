
package tp.ClinicaOdontologica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.ClinicaOdontologica.entity.Odontologo;
import tp.ClinicaOdontologica.repository.OdontologoRepository;

import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public List<Odontologo> listarTodos(){
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> buscarOdontologo(Long id){
        return odontologoRepository.findById(id);
    }

    public void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }

    public void actualizarOdontologo(Odontologo odontologo) { odontologoRepository.save(odontologo);}

}
