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
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Clase que se encarga de gestionar el lanzamiento de varias consultas
 * @author Miguel González y Jaime Bárez
 */
public class Consultor {
    private Diccionario diccionario;
    private Analizador analizador;
    private BD baseDatos;
    Configuracion config;
    
    /**
     * Constructor
     */
    public Consultor() throws SQLException, RuntimeException, SAXException, IOException, ParserConfigurationException {
        diccionario = new Diccionario();
        analizador = new Analizador();
        baseDatos = new BD();
        config = new Configuracion();
    }

    /**
     * Lanza las consultas SQL y devuelve un ModeloTabla con los resultados obtenidos
     * @param sentenciasSQL Las sentencias a lanzar
     * @return El model de los resultados del lanzamiento de las consultas
     * @throws SQLException 
     */
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

            //Inicializamos el headTable
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

    /**
     * Completa los nombres de las columnas al modelo genérico
     * @param headTable Contiene las cabeceras de nombres de columnas
     * @param rsConsulta ResultSet recibido
     * @throws SQLException 
     */
    public void completarTraduccionInversaNombresColumnas(String []headTable,  ResultSet rsConsulta) throws SQLException {

        int numColumnas= headTable.length;

        for(int i=1; i<= numColumnas;i++) {
            String nombreColumna = rsConsulta.getMetaData().getColumnName(i);

            if(headTable[i-1].contains("NULL") || headTable[i-1].isEmpty()) {
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
