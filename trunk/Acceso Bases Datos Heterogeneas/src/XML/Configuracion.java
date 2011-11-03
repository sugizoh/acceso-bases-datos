/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Miguel González y Jaime Bárez
 */
public class Configuracion
{
HashMap<String,ConfiguracionXML> configuraciones;

    public Configuracion()
    {
        configuraciones = new HashMap<String,ConfiguracionXML>();

        try {
            // 1. Obteher el objeto DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // 2. Usar DocumentBuilderFactory para crear un DocumentBuilder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 3. Parsear a partir de un archivo
            Document dom = db.parse("configuracion.xml");

            // 1. Obtener el documento raiz
            Element docEle = dom.getDocumentElement();
            // 2. Obtener un nodelist de elementos <basedatos>
            NodeList nl = docEle.getElementsByTagName("basedatos");

            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    //3. Obtenemos el valor de basedatos (el nombre de la traduccion)
                    String nombreBD = ((Element) nl.item(i)).getAttribute("id");

                    //3. Obtenemos los nombres de las tuplas
                    ArrayList<String> tuplas = new ArrayList<String>();

                    NodeList hijos = nl.item(i).getChildNodes();

                    if(hijos != null && hijos.getLength() > 0)
                        for(int j=0; j<hijos.getLength(); j++)
                        {
                            if(!hijos.item(j).getNodeName().equals("#text"))
                                tuplas.add(hijos.item(j).getNodeName());
                        }

                    //4. Creamos la configuración
                    configuraciones.put(nombreBD, new ConfiguracionXML(tuplas, (Element) nl.item(i), nombreBD));
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


    public String getValor(String baseDatos, String valor) {
        String valorBD = configuraciones.get(baseDatos).getValor(valor);
        if(valorBD == null)
            return valor;
        else
            return valorBD;
    }

    public ArrayList<String> getBaseDatos() {
        ArrayList<String> diccionarios = new ArrayList<String>();
        for(int i=0; i<configuraciones.size(); i++)
            diccionarios.add(configuraciones.get(i).getNombreBD());
        return diccionarios;
    }

    public int numBaseDatos() {
        return configuraciones.size();
    }
}

class ConfiguracionXML
{
    HashMap<String,String> valores;
    String nombreBD;

    //Se rellena el diccionario
    public ConfiguracionXML(ArrayList<String> tuplas, Element elemento, String nombreBD) {
        valores = new HashMap<String,String>();
        this.nombreBD = nombreBD;

        for(int i=0; i<tuplas.size(); i++) {
            valores.put(tuplas.get(i), obtenerTexto(elemento, tuplas.get(i)));
        }
    }

    //Se le pasa elemento que contiene un listado de hijos. Se busca el nodo dado por el nombreValor
    //Y se accede al contenido de su valor
    private String obtenerTexto(Element elemento, String nombreValor) {
        String texto = null;

        NodeList nl = elemento.getElementsByTagName(nombreValor);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            texto = el.getFirstChild().getNodeValue();
        }
        return texto;
    }

    //Se devuelve la traducción de una palabra
    public String getValor(String key) {
        return (String) valores.get(key);
    }

    public String getNombreBD() {
        return nombreBD;
    }
}