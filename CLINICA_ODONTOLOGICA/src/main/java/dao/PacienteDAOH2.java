package dao;

import model.Domicilio;
import model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class PacienteDAOH2 implements iDao<Paciente>{
    private static final Logger logger= Logger.getLogger(PacienteDAOH2.class);
    private static String SQL_INSERT="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO,DOMICILIO_ID) VALUES(?,?,?,?,?)";
    @Override
    public Paciente guardar(Paciente paciente) {

        logger.info("inicio de operaciones guarado de un paciente");
        Connection connection= null;

        try{
            connection= BD.getConnection();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            Domicilio domicilio= daoAux.guardar(paciente.getDomicilio());
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5,domicilio.getId());
            psInsert.execute();
            ResultSet clave= psInsert.getGeneratedKeys();

            while (clave.next()){
                paciente.setId(clave.getInt(1));
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
                  }
        return paciente;
    }

    @Override
    public Paciente buscar(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public List<Paciente> buscarTodos() {
        return null;
    }
}
