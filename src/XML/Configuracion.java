package XML;

import Utilidades.XML;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * @author Miguel González y Jaime Bárez
 * Clase Configuracion
 */
public class Configuracion extends XML
{
    /**
     * Constructor de la clase Configuracion
     */
    public Configuracion() throws RuntimeException, SAXException, IOException, ParserConfigurationException
    {
        super("configuracion.xml", "basedatos");
    }

    /**
     * Función que devuelve el valor de una configuración de una base de datos
     * @param baseDatos Base de datos de la que se obtiene la configuración
     * @param valor Valor a obtener
     * @return Devuelve el valor
     */
    public String getValor(String baseDatos, String valor) {
        //Devolvemos la configuración para esa base de datos
        HashMap<String,String> configuracion = (HashMap<String,String>) xmlLeido.get(baseDatos);
        //Si no existe la base de datos
        if(configuracion == null) {
            return null; //Devolvemos nulo
        } else {
            return configuracion.get(valor.toUpperCase()); //Devolvemos el valor para esa base de datos
        }
    }

    /**
     * Devuelve un ArrayList con los nombres de as bases de datos
     * @return Devuelve los nombres de las bases de datos
     */
    public ArrayList<String> getBaseDatos() {
        ArrayList<String> basesDatos = new ArrayList<String>();

        //Iteramos obteniendo las claves
        Iterator it = xmlLeido.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            basesDatos.add(e.getKey().toString());
        }

        return basesDatos;
    }

    /**
     * Función que devuelve el número de base de datos
     * @return Devuelve el número de base de datos
     */
    public int numBaseDatos() {
        return xmlLeido.size();
    }

    /**
     * Función que devuelve el nombre de una base de datos en una posición
     * @param posicion Posición
     * @return Devuelve el nombre de una base de datos
     */
    public String getNombreBaseDatos(int posicion) {
        //Iteramos para obtener la clave en la posición iterada
        int posActual = 0;
        String hashCode = null;
        Iterator it = xmlLeido.entrySet().iterator();
        while (it.hasNext() && posActual <= posicion) {
            posActual++;
            Map.Entry e = (Map.Entry)it.next();
            hashCode = e.getKey().toString();
        }

        return hashCode;
    }
}