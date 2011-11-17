/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

import XML.Diccionario;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Miguel González y Jaime Bárez
 */
public class Traductor
{
    Diccionario diccionario;
    Analizador analizador;


    public Traductor()
    {
        diccionario = new Diccionario();
        analizador = new Analizador();
    }

    public HashMap<String, String> getConsultasTraducidas(String consultaSQL) throws Exception {
        //Preanalizamos el sql para sustituir el * por los nombres de las columnas
        if(consultaSQL.contains("*")) {
            //Obtenemos las tablas del esquema para el primer diccionario (las columnas se van a llamar igual en el resto dediccionarios, mismo esquea)
            ArrayList<String> nombresTablas = diccionario.getTablasDiccionario();
            //Obtenemos las columnas para cada tabla del esquema
            HashMap<String, ArrayList<String>> columnasBD = diccionario.getColumnasBaseDatos();
            //Creamos una String vacía que rellenaremos con las columnas de las tablas a las que se acceda
            String asteriscoSustituir = "";
            //Comprobamos si la consulta tiene las siguientes tablas
            for(int i=0; i<nombresTablas.size(); i++) {
                String nombreTablaActual = nombresTablas.get(i);
                if(consultaSQL.toUpperCase().contains(nombreTablaActual)) { //Si contiene la tabla
                    ArrayList<String> nombreColumnas = columnasBD.get(nombresTablas.get(i).toUpperCase());

                    for(int j=0; j<nombreColumnas.size(); j++) {
                        if(diccionario.claveAmbigua(nombreColumnas.get(j))) { //Si es clave ambigua
                            asteriscoSustituir += nombreTablaActual + "." + nombreColumnas.get(j) + ", "; //Ponemos el nombre de la tabla a la que corresponde
                        } else {
                            asteriscoSustituir += nombreColumnas.get(j) + ", ";
                        }
                    }
                }
            }

            //Quitamos los dos ultimos carácteres ", "
            asteriscoSustituir = asteriscoSustituir.substring(0, asteriscoSustituir.length() - 2);
            //Sustituimos
            if(!asteriscoSustituir.equals("")) { //Si se han encontado columnas a sustituir
                consultaSQL = consultaSQL.replaceAll("\\*", asteriscoSustituir);
            }
        }


        //Creamos el HashMap que contendrá las consultas traducidas
        HashMap<String, String> consultasTraducidas = new HashMap<String, String>();
        //Analizamos el sql
        ArrayList<Tupla> consultaTroceada = analizador.desmembrar(consultaSQL);
        //Recorremos todos los diccionarios y vamos creando las consultas traducidas
        for(int i=0; i<diccionario.numDiccionarios(); i++) {
            String cadenaSqlTraducida = "";
            //Obtenemos el nombre de la base de datos
            String nombreDiccionario = diccionario.getNombreDiccionario(i);
            //Formamos la cadena SQL Traducida
            for(int j=0; j<consultaTroceada.size(); j++) {
                if(consultaTroceada.get(j).isEsSeparador()) { //Si es reservada no hacemos nada
                    cadenaSqlTraducida += consultaTroceada.get(j).getPalabra();
                } else { //Si no lo es
                    cadenaSqlTraducida += diccionario.getTraduccionPalabraAutomatica(nombreDiccionario,consultaTroceada.get(j).getPalabra());
                }
            }
            //Guardamos la traducción
            consultasTraducidas.put(nombreDiccionario, cadenaSqlTraducida);
        }
        //Devolvemos las traducciones
        return consultasTraducidas;
    }
}