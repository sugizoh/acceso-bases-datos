/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author JaimeInves
 */
public class Consultor {
    public ArrayList<String> lanzarConsulta(HashMap<String, String> sentenciasSQL) {
        ArrayList<String> tuplas = new ArrayList<String>();
        Configuracion config = new Configuracion();
        BD baseDatos = new BD();
        for(int i=0; i<config.numBaseDatos(); i++) {
            String nombreBD = config.getNombreBaseDatos(i);
            baseDatos.abrirBD(config.getValor(nombreBD, "conexion"), config.getValor(nombreBD, "usuario"), config.getValor(nombreBD, "password"));
            ResultSet rsConsulta = baseDatos.consultar(sentenciasSQL.get(nombreBD));
            try {
                int numColumnas = rsConsulta.getMetaData().getColumnCount();
                while (rsConsulta.next()) {
                    String tupla = "";
                    for(int j=1; j<=numColumnas; j++) {
                        String valor = rsConsulta.getString(j).toString();//MIRAR
                        tupla += valor + " ";
                    }
                    tuplas.add(tupla);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Consultor.class.getName()).log(Level.SEVERE, null, ex);
            }
                    //
                    //Obtener blablabla
                    //
            baseDatos.cerrar();
        }
        
        //sentenciasSQL.get(this)
        return tuplas;
    }
}
