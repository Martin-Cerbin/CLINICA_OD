package tp.ClinicaOdontologica.service;

import tp.ClinicaOdontologica.dto.TurnoDTO;
import tp.ClinicaOdontologica.entity.Odontologo;
import tp.ClinicaOdontologica.entity.Paciente;
import tp.ClinicaOdontologica.entity.Turno;
import tp.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
@Autowired
    private TurnoRepository turnoRepository;
//metodos manuales
    public TurnoDTO guardarTurno(Turno turno){
        Turno turnoAguardar= turnoRepository.save(turno);
        return turnoATurnoDTO(turnoAguardar);

    }
    public void actualizarTurno(Turno turno){
         turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public Optional<TurnoDTO> buscarPorID(Long id){
        Optional<Turno> turnoABuscar= turnoRepository.findById(id);
        if(turnoABuscar.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoABuscar.get()));
        }else{
           return Optional.empty();
        }
    }

    public List<TurnoDTO> listarTodos(){
        List<Turno> turnos= turnoRepository.findAll();
        List<TurnoDTO> listaDTO= new ArrayList<>();
        for (Turno turno : turnos) {
            listaDTO.add(turnoATurnoDTO(turno));
                    }
        return listaDTO;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        //conversion manual
        TurnoDTO turnoDTO= new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        return turnoDTO;
    }
    public Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        //conversion inversa manual
        Turno turno= new Turno();
        Paciente paciente= new Paciente();
        Odontologo odontologo= new Odontologo();
        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setId(turnoDTO.getId());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        return turno;
    }
}
