package tp.ClinicaOdontologica.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.ClinicaOdontologica.Model.Turno;
import tp.ClinicaOdontologica.Repository.TurnoDAOLista;
import tp.ClinicaOdontologica.Repository.iDao;

import java.util.List;

@Service
public class TurnoService {

    private iDao<Turno> turnoiDao;

   @Autowired
    public TurnoService() {
        turnoiDao= new TurnoDAOLista();
    }

    //metodos manuales
    public List<Turno> obtenerTodosLosTurnos(){
        return turnoiDao.buscarTodos();
    }
    public Turno buscarPorId(Integer id){
        return turnoiDao.buscar(id);
    }
    public void eliminarTurno(Integer id){
        turnoiDao.eliminar(id);
    }
    public void actualizarTurno(Turno turno){
        turnoiDao.actualizar(turno);
    }
    public Turno guardarTurno(Turno turno){
        return turnoiDao.guardar(turno);
    }
}
