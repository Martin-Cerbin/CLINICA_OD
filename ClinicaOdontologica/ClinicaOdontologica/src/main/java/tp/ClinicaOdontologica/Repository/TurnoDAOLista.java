package tp.ClinicaOdontologica.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import tp.ClinicaOdontologica.Model.Turno;

import java.util.ArrayList;
import java.util.List;



public class TurnoDAOLista implements iDao<Turno> {

@Autowired
    private List<Turno> turnoLista= new ArrayList<>();

   /* public TurnoDAOLista() {
        turnoLista= new ArrayList<>();
    }*/

    @Override
    public Turno guardar(Turno turno) {
        turnoLista.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        //como recorremos una lista?
        for (Turno turno:turnoLista) {
            //una condicion
            if(turno.getId().equals(id)){
                return turno;
            }
                 }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        //buscarlo
        Turno turnoBuscado= buscar(id);
        turnoLista.remove(turnoBuscado);

    }

    @Override
    public void actualizar(Turno turno) {
        eliminar(turno.getId());
        guardar(turno);

    }

    @Override
    public List<Turno> buscarTodos() {

        return turnoLista;
    }

    @Override
    public Turno buscarPorString(String valor) {
        return null;
    }
}
