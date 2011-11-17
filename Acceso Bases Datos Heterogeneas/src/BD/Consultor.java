package BD;

import XML.Configuracion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel González y Jaime Bárez
 */
public class Consultor {
    public ArrayList<String> lanzarConsulta(HashMap<String, String> sentenciasSQL) {
        ArrayList<String> tuplas = new ArrayList<String>();
        Configuracion config = new Configuracion();
        BD baseDatos = new BD();
        for(int i=0; i<config.numBaseDatos(); i++) {
            String nombreBD = config.getNombreBaseDatos(i);
            
            try {
                baseDatos.abrirBD(config.getValor(nombreBD, "conexion"), config.getValor(nombreBD, "usuario"), config.getValor(nombreBD, "password"));
                ResultSet rsConsulta = baseDatos.consultar(sentenciasSQL.get(nombreBD));
            
                int numColumnas = rsConsulta.getMetaData().getColumnCount();
                while (rsConsulta.next()) {
                    String tupla = "";
                    for(int j=1; j<=numColumnas; j++) {
                        String valor = rsConsulta.getString(j).toString();//MIRAR
                        tupla += valor + " ";
                    }
                    tuplas.add(tupla);
                
                baseDatos.cerrar();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Consultor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException nl) {
                System.out.println("Base de datos " + nombreBD + " inaccesible");
            }
                    //
                    //Obtener blablabla
                    //
            
        }
        
        //sentenciasSQL.get(this)
        return tuplas;
    }
}
