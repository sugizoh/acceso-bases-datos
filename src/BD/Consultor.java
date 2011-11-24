/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import GUI.ModeloTabla;
import Utilidades.Analizador;
import Utilidades.Tupla;
import XML.Configuracion;
import XML.Diccionario;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Miguel González y Jaime Bárez
 */
public class Consultor {
    private Diccionario diccionario;
    private Analizador analizador;
    private BD baseDatos;
    Configuracion config;

    public Consultor() throws SQLException, RuntimeException, SAXException, IOException, ParserConfigurationException {
        diccionario = new Diccionario();
        analizador = new Analizador();
        baseDatos = new BD();
        config = new Configuracion();
    }

    public ModeloTabla lanzarConsulta(HashMap<String, String> sentenciasSQL) throws SQLException {
        String headTable[] = null;
        String datos[][] = null;
        int numColumnas = 0;
        ArrayList<String[]> arrayDatos = new ArrayList<String[]>();
        
        for(int i=0; i<config.numBaseDatos(); i++) {
            String nombreBD = config.getNombreBaseDatos(i);
            baseDatos.abrirBD(config.getValor(nombreBD, "conexion"), config.getValor(nombreBD, "usuario"), config.getValor(nombreBD, "password"));
            ResultSet rsConsulta = baseDatos.consultar(sentenciasSQL.get(nombreBD));
            
            numColumnas = rsConsulta.getMetaData().getColumnCount();

            //En la primera base de datos obtenemos los datos de la cabecera de la tabla
            if(i == 0) {
                headTable = new String[numColumnas];
                for(int j=0; j<numColumnas; j++) {
                    headTable[j] = new String();
                }
            }

            //Traducimos la headTable
            completarTraduccionInversaNombresColumnas(headTable, rsConsulta);

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
            //Cerramos la conexión de la base de datos
            baseDatos.cerrar();
        }

        datos = new String[arrayDatos.size()][numColumnas];

        for(int i=0; i<arrayDatos.size(); i++)
            datos[i] = arrayDatos.get(i);

        //sentenciasSQL.get(this)
        return new ModeloTabla(datos,headTable);
    }

    public void completarTraduccionInversaNombresColumnas(String []headTable,  ResultSet rsConsulta) throws SQLException {

        int numColumnas= headTable.length;

        for(int i=1; i<= numColumnas;i++) {
            String nombreColumna = rsConsulta.getMetaData().getColumnName(i);

            if(!nombreColumna.contains("NULL") || headTable[i -1].contains(nombreColumna)) {
                ArrayList<Tupla> columnaTroceada = analizador.desmembrar(nombreColumna);
                String nuevoColumnaTraducidaInversa = "";
                for (int j = 0; j < columnaTroceada.size(); j++) {
                    if (columnaTroceada.get(j).isEsSeparador()) {
                        nuevoColumnaTraducidaInversa += columnaTroceada.get(j).getPalabra();
                    } else {
                        nuevoColumnaTraducidaInversa += diccionario.getTraduccionPalabraAutomaticaInversa(columnaTroceada.get(j).getPalabra());
                    }

                    headTable[i - 1] = nuevoColumnaTraducidaInversa;
                }
            }
        }
    }
}
