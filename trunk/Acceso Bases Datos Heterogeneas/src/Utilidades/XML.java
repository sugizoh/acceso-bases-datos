package Utilidades;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.soap.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Miguel Gonzalez y Jaime Bárez
 */
public class XML
{
    protected HashMap<String, HashMap<String,String>> xmlLeido;

    public XML(String ficheroXML, String tagName) {
        xmlLeido = new HashMap<String, HashMap<String,String>>();

        try {
            // 1. Obteher el objeto DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // 2. Usar DocumentBuilderFactory para crear un DocumentBuilder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 3. Parsear a partir de un archivo
            Document dom = db.parse(ficheroXML);

            // 1. Obtener el documento raiz
            Element docEle = dom.getDocumentElement();
            // 2. Obtener un nodelist de elementos tagName
            NodeList nl = docEle.getElementsByTagName(tagName);

            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    //3. Obtenemos el nombre del objeto del tagName
                    String nombreBD = ((Element) nl.item(i)).getAttribute("id");

                    //Creamos una HashMap que contendrá los datos/valores
                    HashMap<String,String> conf = new HashMap<String,String>();

                    //Obtenemos los hijos de la configuración
                    NodeList hijos = nl.item(i).getChildNodes();
                    if(hijos != null && hijos.getLength() > 0) {
                        //Recorremos todos los hijos para obtener sus valores
                        for(int j=0; j<hijos.getLength(); j++)
                        {
                            if(hijos.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                //Nombre de la configuración
                                String datoConfiguracion = hijos.item(j).getNodeName();
                                //Valor de la configuración
                                String valorConfiguracion = obtenerTexto((Element) nl.item(i),datoConfiguracion);
                                //Añadimos el valor de la configuración
                                conf.put(datoConfiguracion.toUpperCase(), valorConfiguracion);
                            }
                        }
                    }

                    //Añadimos la configuración
                    xmlLeido.put(nombreBD,conf);
                }
            }
        } catch (SAXException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
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
}
