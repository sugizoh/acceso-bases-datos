/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.soap.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * @author Miguel Gonzalez y Jaime Bárez
 * Clase XML
 */
public class XML
{
    protected HashMap<String, Object> xmlLeido;

    /**
     * Constructor de la clase XML sobrecargada
     * @param ficheroXML Nombre del fichero XML
     * @param subArboles SubÁrboles que contendrá el fichero XML
     * @throws RuntimeException Excepción
     */
    public XML(String ficheroXML, String... subArboles) throws RuntimeException, SAXException, IOException, ParserConfigurationException {
        if(subArboles.length == 0) { //Comprobamos que se pasan subArboles a leer (no tiene sentido sino se hace)
            throw new RuntimeException("No se le pasan subArboles al leer el XML");
        }
        //Creamos el HashMap xmlLeido
        xmlLeido = new HashMap<String, Object>();

        //Obteher el objeto DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //Usar DocumentBuilderFactory para crear un DocumentBuilder
        DocumentBuilder db = dbf.newDocumentBuilder();
        //Parsear a partir de un archivo
        Document dom = db.parse(ficheroXML);
        //Obtener el documento raiz
        Element docEle = dom.getDocumentElement();
        //Cargamos recursivamente el HashMap xmlLeido
        xmlLeido = cargaDomRecursivo(docEle, subArboles);
    }

    /**
     * Carga recursiva del dom
     * @param docEle Nodo en el que estamos
     * @param subArboles SubÁrboles que quedan por leer
     * @return Devuelve un HashMap con la nueva generación de ese nodo
     */
    private HashMap<String, Object> cargaDomRecursivo(Element docEle, String... subArboles) {
        //Creamos el HashMap
        HashMap<String, Object> conf = new HashMap<String, Object>();
        //Obtenemos el NodeList con los elementos dados por el tagName del primer elemento
        NodeList nl = docEle.getElementsByTagName(subArboles[0]);
        //Comprobamos que haya podido obtener el elemento
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                //3. Obtenemos el nombre del objeto del tagName
                String nombreTagName = ((Element) nl.item(i)).getAttribute("id");
                //Si estamos en la raíz del DOM
                if(subArboles.length == 1) {
                    //Obtenemos los hijos de la raíz
                    NodeList hijos = ((Element) nl.item(i)).getChildNodes();
                    //Creamos una HashMap que contendrá los datos/valores obtenidos
                    HashMap<String,Object> confValores = obtenerValores((Element) nl.item(i), hijos);
                    //Añadimos a la configuración el nombre del hijo de la raíz y los valores
                    conf.put(nombreTagName.toUpperCase(), confValores);
                } else { //Hacedemos a otro subArbol del nodo
                    //Creamos el próximo subArbol
                    String []proxSubArbol = new String[subArboles.length - 1];
                    for(int j=1; j<subArboles.length; j++)
                        proxSubArbol[j - 1] = subArboles[j];
                    //Añadimos a la configuración el subArbol siguiente
                    conf.put(nombreTagName.toUpperCase(), cargaDomRecursivo((Element) nl.item(i), proxSubArbol));
                }
            }
            //Devolvemos la configuración
            return conf;
        } else {
            throw new RuntimeException("Problema al leer el XML, no se ha encontrado la id=\"" + subArboles[0] + "\"");
        }
    }

    /**
     * Función que obtiene los valores en un nodo final (hijo)
     * @param padre Nodo padre
     * @param hijos Lista con los nodos hijo
     * @return Devuelve un HashMap con los hijos y sus valores
     */
    private HashMap<String, Object> obtenerValores(Element padre, NodeList hijos)
    {
        HashMap<String, Object> valores = new HashMap<String,Object>();

        if(hijos != null && hijos.getLength() > 0) {
            //Recorremos todos los hijos para obtener sus valores
            for(int j=0; j<hijos.getLength(); j++)
            {
                if(hijos.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    //Nombre de la configuración
                    String datoConfiguracion = hijos.item(j).getNodeName();
                    //Valor de la configuración
                    String valorConfiguracion = obtenerTexto(padre,datoConfiguracion);
                    //Añadimos el valor de la configuración
                    valores.put(datoConfiguracion.toUpperCase(), valorConfiguracion);
                }
            }
        }

        return valores;
    }

    /**
     * Obtiene el texto de un nodo
     * @param elemento Nodos hijos
     * @param nombreEtiqueta Etiqueta
     * @return Devuelve el valor del nodo cuya etiqueta corresponde
     */
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
