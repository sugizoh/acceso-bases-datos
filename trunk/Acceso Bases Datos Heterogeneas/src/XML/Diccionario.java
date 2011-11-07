/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Miguel González y Jaime Bárez
 */
public class Diccionario
{
    HashMap<String,TraduccionXML> traducciones;

    public Diccionario()
    {
        traducciones = new HashMap<String,TraduccionXML>();
        
        try {
            // 1. Obteher el objeto DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // 2. Usar DocumentBuilderFactory para crear un DocumentBuilder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 3. Parsear a partir de un archivo
            Document dom = db.parse("traducciones.xml");

            // 1. Obtener el documento raiz
            Element docEle = dom.getDocumentElement();
            // 2. Obtener un nodelist de elementos <basedatos>
            NodeList nl = docEle.getElementsByTagName("basedatos");

            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    //3. Obtenemos el valor de basedatos (el nombre de la traduccion)
                    String nombreTraduccion = ((Element) nl.item(i)).getAttribute("id");

                    //Creamos una traducción
                    TraduccionXML traduccion = new TraduccionXML(nombreTraduccion);

                    //Obtenemos los hijos de la traducción
                    NodeList hijos = nl.item(i).getChildNodes();

                    //Recorremos todos los hijos
                    if(hijos != null && hijos.getLength() > 0) {
                        for(int j=0; j<hijos.getLength(); j++)
                        {
                            if(!hijos.item(j).getNodeName().equals("#text")) {
                                //Obtenemos el nombre de la traducción
                                String nombreValorTraduccion = (String)hijos.item(j).getNodeName();
                                //Obtenemos la palabra traducida
                                String traduccionPalabra = obtenerTexto((Element) nl.item(i), nombreValorTraduccion);
                                traduccion.add(nombreValorTraduccion, traduccionPalabra);
                            }
                        }
                    }

                    //Añadimos la traducción
                    traducciones.put(nombreTraduccion, traduccion);
                }
            }
        } catch (SAXException ex) {
            Logger.getLogger(Diccionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Diccionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Diccionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     private String obtenerTexto(Element elemento, String nombreEtiqueta) {
        String texto = null;

        NodeList nl = elemento.getElementsByTagName(nombreEtiqueta);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            texto = el.getFirstChild().getNodeValue();
        }
        return texto;
    }

    public String getTraduccionPalabra(String idioma, String palabra) {
        TraduccionXML traduccion = traducciones.get(idioma);
        String traduccionObtenida = null;
        if(traduccion == null)
            return palabra;
        else
            traduccionObtenida =  traduccion.getTraduccionPalabra(palabra);

        if(traduccionObtenida == null)
            traduccionObtenida = palabra;
        
        return traduccionObtenida;
    }

    public ArrayList<String> getNombresDiccionarios() {
        ArrayList<String> diccionarios = new ArrayList<String>();

        //Iteramos obteniendo las claves
        Iterator it = traducciones.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            diccionarios.add(e.getKey().toString());
        }

        return diccionarios;
    }

    public int numDiccionarios() {
        return traducciones.size();
    }

    public String getNombreDiccionario(int posicion) {
        //Iteramos para obtener la clave en la posición iterada
        int posActual = 0;
        String hashCode = null;
        Iterator it = traducciones.entrySet().iterator();
        while (it.hasNext() && posActual <= posicion) {
            posActual++;
            Map.Entry e = (Map.Entry)it.next();
            hashCode = e.getKey().toString();
        }

        return hashCode;
    }
}

//Contiene una traducción de un diccionario
class TraduccionXML
{
    HashMap<String,String> palabras;
    String nombreTraduccion;

    public TraduccionXML(String nombreTraduccion)
    {
        palabras = new HashMap<String,String>();
        this.nombreTraduccion = nombreTraduccion;
    }

    public void add(String nombreValorTraducido, String nombreTraducido)
    {
        palabras.put(nombreValorTraducido.toUpperCase(), nombreTraducido);
    }

    //Se devuelve la traducción de una palabra
    public String getTraduccionPalabra(String palabra) {
        return (String) palabras.get(palabra.toUpperCase());
    }

    public String getNombreTraduccion() {
        return nombreTraduccion;
    }
}