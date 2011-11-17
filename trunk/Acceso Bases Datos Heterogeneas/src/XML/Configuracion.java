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

    public String getValor(String baseDatos, String valor) {
        HashMap<String,String> configuracion = xmlLeido.get(baseDatos);
        if(configuracion == null)
            return null;
        else
            return configuracion.get(valor.toUpperCase());
    }

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

    public int numBaseDatos() {
        return xmlLeido.size();
    }

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