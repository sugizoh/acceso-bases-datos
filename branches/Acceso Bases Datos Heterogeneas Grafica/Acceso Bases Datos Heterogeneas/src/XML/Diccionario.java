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
public class Diccionario extends XML
{

    public Diccionario()
    {
        super("traducciones.xml", "basedatos");
    }

    public String getTraduccionPalabra(String idioma, String palabra) {
        HashMap<String,String> traduccion = xmlLeido.get(idioma);
        String traduccionObtenida = null;
        if(traduccion == null)
            return palabra;
        else
            traduccionObtenida =  traduccion.get(palabra);

        if(traduccionObtenida == null)
            traduccionObtenida = palabra;
        
        return traduccionObtenida;
    }

    public ArrayList<String> getNombresDiccionarios() {
        ArrayList<String> diccionarios = new ArrayList<String>();

        //Iteramos obteniendo las claves
        Iterator it = xmlLeido.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            diccionarios.add(e.getKey().toString());
        }

        return diccionarios;
    }

    public int numDiccionarios() {
        return xmlLeido.size();
    }

    public String getNombreDiccionario(int posicion) {
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