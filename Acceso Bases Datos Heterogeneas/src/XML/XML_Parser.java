/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author paracaidista
 */
public class XML_Parser
{
    private Document dom;
    ArrayList<TraduccionXML> traducciones;

    public XML_Parser()
    {
        traducciones = new ArrayList<TraduccionXML>();
        
        try {
            // 1. Obteher el objeto DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // 2. Usar DocumentBuilderFactory para crear un DocumentBuilder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 3. Parsear a partir de un archivo
            dom = db.parse("traducciones.xml");

            // 1. Obtener el documento raiz
            Element docEle = dom.getDocumentElement();
            // 2. Obtener un nodelist de elementos <basedatos>
            NodeList nl = docEle.getElementsByTagName("basedatos");

            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    //Obtenemos los nombres de las tuplas
                    ArrayList<String> tuplas = new ArrayList<String>();

                    NodeList hijos = nl.item(i).getChildNodes();

                    if(hijos != null && hijos.getLength() > 0)
                        for(int j=0; j<hijos.getLength(); j++)
                        {
                            if(!hijos.item(j).getNodeName().equals("#text"))
                                tuplas.add(hijos.item(j).getNodeName());
                        }

                    //Creamos la traducción
                    traducciones.add(new TraduccionXML(tuplas, (Element) nl.item(i)));
                }
            }
        } catch (SAXException ex) {
            Logger.getLogger(XML_Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XML_Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XML_Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getTraduccionPalabra(String palabra) {
        return traducciones.get(0).getTraduccionPalabra(palabra);
    }
}

class TraduccionXML
{
    ArrayList<TuplaTraduccion> palabras;

    public TraduccionXML(ArrayList<String> tuplas, Element elemento) {
        palabras = new ArrayList<TuplaTraduccion>();
        
        for(int i=0; i<tuplas.size(); i++) {
            palabras.add(new TuplaTraduccion(tuplas.get(i), obtenerTexto(elemento, tuplas.get(i))));
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

    public String getTraduccionPalabra(String palabra) {
        String traduccion = null;

        //Buscamos la palabra
        for(int i=0; i<palabras.size() && traduccion == null; i++)
        {
            if(palabras.get(i).getPalabraOriginal().equals(palabra)) //Si es la palabra buscada
                traduccion = palabras.get(i).getPalabraTraducida(); //Obtenemos su traducción
        }

        return traduccion;
    }
}

class TuplaTraduccion
{
    private String palabraOriginal;
    private String palabraTraducida;

    public TuplaTraduccion(String palabraOriginal, String palabraTraducida) {
        this.palabraOriginal = palabraOriginal;
        this.palabraTraducida = palabraTraducida;
    }

    public String getPalabraOriginal() {
        return palabraOriginal;
    }

    public String getPalabraTraducida() {
        return palabraTraducida;
    }
}