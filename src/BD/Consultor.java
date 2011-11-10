/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import GUI.ModeloTabla;
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
    public ModeloTabla lanzarConsulta(HashMap<String, String> sentenciasSQL) {
        String headTable[] = null;
        String datos[][] = null;
        int numColumnas = 0;
        ArrayList<String[]> arrayDatos = new ArrayList<String[]>();

        Configuracion config = new Configuracion();
        BD baseDatos = new BD();
        for(int i=0; i<config.numBaseDatos(); i++) {
            String nombreBD = config.getNombreBaseDatos(i);
            baseDatos.abrirBD(config.getValor(nombreBD, "conexion"), config.getValor(nombreBD, "usuario"), config.getValor(nombreBD, "password"));
            ResultSet rsConsulta = baseDatos.consultar(sentenciasSQL.get(nombreBD));
            try {
                numColumnas = rsConsulta.getMetaData().getColumnCount();

                //En la primera base de datos obtenemos los datos de la cabecera de la tabla
                headTable = new String[numColumnas];
                for(int j=0; j<numColumnas;j++)
                    headTable[j] = rsConsulta.getMetaData().getColumnName(j+1);

                int consultaActual = 0;
                while (rsConsulta.next()) {
                    String []filaDatos = new String[numColumnas];
                    for(int j=1; j<=numColumnas; j++) {
                        String valor = rsConsulta.getString(j).toString();
                        filaDatos[j-1] = valor;
                    }
                    arrayDatos.add(filaDatos);
                    consultaActual++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Consultor.class.getName()).log(Level.SEVERE, null, ex);
            }
            baseDatos.cerrar();
        }

        datos = new String[arrayDatos.size()][numColumnas];

        for(int i=0; i<arrayDatos.size(); i++)
            datos[i] = arrayDatos.get(i);

        //sentenciasSQL.get(this)
        return new ModeloTabla(datos,headTable);
    }
}
