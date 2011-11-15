/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML;

import Utilidades.XML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Miguel González y Jaime Bárez
 */
public class Configuracion extends XML
{
    public Configuracion()
    {
        super("configuracion.xml", "basedatos");
    }

    //Corregido
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

    //Corregido
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

    //Corregido
    public int numBaseDatos() {
        return xmlLeido.size();
    }

    //Corregido
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