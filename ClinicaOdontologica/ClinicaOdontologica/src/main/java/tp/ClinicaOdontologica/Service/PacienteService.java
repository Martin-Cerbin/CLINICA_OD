package tp.ClinicaOdontologica.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.ClinicaOdontologica.Model.Paciente;
import tp.ClinicaOdontologica.Repository.PacienteDAOH2;
import tp.ClinicaOdontologica.Repository.iDao;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private iDao<Paciente> pacienteiDao= new PacienteDAOH2();
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorID(Integer id){
        return pacienteiDao.buscar(id);
    }
    public void eliminarPaciente(Integer id){
        pacienteiDao.eliminar(id);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }
    public List<Paciente> obtenerPacientes(){
        return pacienteiDao.buscarTodos();
    }

    public Paciente buscarPorEmail(String correo){
        return pacienteiDao.buscarPorString(correo);
    }
}
