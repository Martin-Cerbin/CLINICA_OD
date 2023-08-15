package dao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;


public class OdontologoDAO {

    private static final Logger logger= Logger.getLogger(OdontologoDAO.class);
    private Odontologo odontologo;
    private ArrayList<Odontologo> arrayOdontologo = new ArrayList<>();

    // =========== CONSTRUCTOR ============ //

    public OdontologoDAO(Odontologo odontologo, ArrayList<Odontologo> lista) {
        this.odontologo = odontologo;
    }

    // =========== GET AND SET ============ //

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public ArrayList<Odontologo> getListaOdontologo() {
        return arrayOdontologo;
    }

    public void setListaOdontologo(ArrayList<Odontologo> listaOdontologo) {
        this.arrayOdontologo = listaOdontologo;
    }


    // =========== METODOS ============ //

    public void guardarOdontologo(Odontologo o){
        arrayOdontologo.add(o);
        logger.info("Se ha guardado un odontologo");
    }

    public void listarOdontologos(){

        for (int x=0;x<arrayOdontologo.size();x++){
            System.out.printf(String.valueOf(arrayOdontologo.get(x)));
        }
        logger.info("se han listado los odontologos");
    }


}
