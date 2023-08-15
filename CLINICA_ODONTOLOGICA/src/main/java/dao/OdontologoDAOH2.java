package dao;

import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{

    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT_ODONTOLOGO = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, NUMERO_DE_MATRICULA) VALUES (?,?,?,)";
    private static final String SQL_SELECT_ALL_ODONTOLOGOS = "SELECT * FROM ODONTOLOGOS WHERE ID=?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        try{
            connection = BD.getConnection();
            PreparedStatement psInsertOdontologo = connection.prepareStatement(SQL_INSERT_ODONTOLOGO, Statement.RETURN_GENERATED_KEYS);
            psInsertOdontologo.setString(1, odontologo.getNombre());
            psInsertOdontologo.setString(2, odontologo.getApellido());
            psInsertOdontologo.setInt(3, odontologo.getNumeroDeMatricula());
            psInsertOdontologo.execute();
            ResultSet rs =  psInsertOdontologo.getGeneratedKeys();
            while(rs.next()){
                odontologo.setId(rs.getInt(1));
                logger.info("Fin de la operacion guardar");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Odontologo buscar(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public List<Odontologo> buscarTodos() {

        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            connection = BD.getConnection();

            PreparedStatement psSelectAll = connection.prepareStatement(SQL_SELECT_ALL_ODONTOLOGOS);
            int id = 0;
            psSelectAll.setInt(1,id);
            ResultSet rs = psSelectAll.executeQuery();
            while(rs.next()){

                Integer idOdontologo = rs.getInt("id");
                Integer numeroMatricula = rs.getInt("numeroDeMatricula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Odontologo odontologo = new Odontologo();
                odontologo.setId(idOdontologo);
                odontologo.setNombre(nombre);
                odontologo.setNumeroDeMatricula(numeroMatricula);
                odontologo.setApellido(apellido);

                odontologo.add(odontologo);

                logger.info("Fin de la operacion guardar");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return odontologos;
    }

}