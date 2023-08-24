package tp.ClinicaOdontologica.Repository;

import tp.ClinicaOdontologica.Model.Odontologo;

import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger("OdontologoDAOH2.class");
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=?, WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM ODONTOLOGOS WHERE ID=?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("inicio de operacion de Guardado de un Odontologo : "+odontologo.getId());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getMatricula());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3,odontologo.getApellido());
            psInsert.execute(); // persistimos
            ResultSet clave= psInsert.getGeneratedKeys();
            while(clave.next()){
                odontologo.setId(clave.getInt(1));
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
        return odontologo;
    }

    @Override
    public Odontologo buscar(Integer id) {
        logger.info("inicio de operacion de Buscar un odontologo : "+id);
        Connection connection= null;
        Odontologo odontologo= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1,id);
            ResultSet rs= psSelectOne.executeQuery();
            while(rs.next()){
                //construir el odontologo
                // cada valor de la tabla se lo tengo que setear a un obj odo.
                odontologo= new Odontologo(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
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
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("inicio de operacion de  eliminado segun id : "+id);
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psDelete= connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1,id);
            psDelete.execute();
                    }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void actualizar(Odontologo odontologo) {
        logger.info("inicio de operacion de  Actualizado : "+odontologo.getId());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, odontologo.getMatricula());
            psUpdate.setString(2, odontologo.getNombre());
            psUpdate.setString(3, odontologo.getApellido());
            psUpdate.execute(); //persistencia
                    }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("inicio de operacion de buscado de todos los odontologos");
        Connection connection= null;
        List<Odontologo> listaOdontologos= new ArrayList<>();
        Odontologo odontologo= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectAll= connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs= psSelectAll.executeQuery();
            while(rs.next()){
                odontologo= new Odontologo(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
                //lo necesito agregar a la lista
                listaOdontologos.add(odontologo);
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
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarPorString(String valor) {
        logger.info("inicio de operacion de : ");
        Connection connection= null;
        try{
            connection= BD.getConnection();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
