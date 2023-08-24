package tp.ClinicaOdontologica.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.ClinicaOdontologica.Model.Odontologo;
import tp.ClinicaOdontologica.Repository.OdontologoDAOH2;
import tp.ClinicaOdontologica.Repository.iDao;

import java.util.List;

@Service
public class OdontologoService {
    private iDao<Odontologo> odontologoiDao;
    @Autowired
    public OdontologoService() {
        odontologoiDao= new OdontologoDAOH2();
    }
    //metodos manuales
    public Odontologo guardarOdontologo(Odontologo odontologo){
         return odontologoiDao.guardar(odontologo);
    }
    public Odontologo buscarOdontologo(Integer id){
        return odontologoiDao.buscar(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoiDao.actualizar(odontologo);
    }
    public void eliminarOdontologo(Integer id){
        odontologoiDao.eliminar(id);
    }
    public List<Odontologo> listarTodos(){
        return odontologoiDao.buscarTodos();
    }
}
