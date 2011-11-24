/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

import XML.Diccionario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase Traductor
 * @author Miguel González y Jaime Bárez
 */
public class Traductor
{
    Diccionario diccionario;
    Analizador analizador;

    /**
     * Constructor del objeto Traductor
     */
    public Traductor()
    {
        diccionario = new Diccionario();
        analizador = new Analizador();
    }

    /**
     * Función que dada una consulta SQL Devuelve las columnas con el formato "columna, columna, columna "
     * @param consultaSQL Consulta SQL
     * @return Devuelve el nombre de las columnas según una consultaSQL dada.
     */
    private String obtenerColumnasRemplazarAsterisco(String consultaSQL) {
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

        asteriscoSustituir = asteriscoSustituir.substring(0, asteriscoSustituir.length() - 2);

        return asteriscoSustituir;
    }

    /**
     * Dada una consulta SQL la devuelve sin el asterisco
     * @param consultaSQL Consulta SQL a analizar
     * @return Dada una consulta SQL la devuelve sin el asterisco
     */
    private String obtenerConsultaSinASterisco(String consultaSQL)
    {
        //Preanalizamos el sql para sustituir el * por los nombres de las columnas
        String regexAsterisco = "select[ ]*\\*[ ]*from|select[ ]*\\*[ ]*,"; //Ej. select * from tabla .... Ò select *, .... , ...

        Pattern patronAsterisco = Pattern.compile(regexAsterisco, Pattern.CASE_INSENSITIVE); //Compilamos el patrón

        String consultaOriginal = consultaSQL;
        Boolean encontradaCoincidencia;

        do {
            encontradaCoincidencia = false;
            Matcher matcherAsterisco = patronAsterisco.matcher(consultaSQL);

            if(matcherAsterisco.find()) //Si hay una coincidencia
            {
                //Dividimos la consultaSQL
                String consultaSQLInicio = consultaSQL.substring(0, matcherAsterisco.start());
                String consultaSQLAsterisco = consultaSQL.substring(matcherAsterisco.start(), matcherAsterisco.end());
                String consultaSQLFin = consultaSQL.substring(matcherAsterisco.end(), consultaSQL.length());

                //Ponemos espacios al principio y al final. Por si es SELECT*FROM -> no ponga SELECTcampo1FROM que da error, sea SELECT campo1 FROM
                consultaSQLAsterisco = consultaSQLAsterisco.replaceFirst(("\\*"), " " + obtenerColumnasRemplazarAsterisco(consultaOriginal) + " ");

                //Rearmamos la consultaSQL
                consultaSQL = consultaSQLInicio + consultaSQLAsterisco + consultaSQLFin;

                encontradaCoincidencia = true;
            }
        } while(encontradaCoincidencia);
        return consultaSQL;
    }

    /**
     * Dada una consulta SQL devuelve un HashMap un SQL con la cadena traducida para cada tipo de base de datos
     * @param consultaSQL Consulta SQL A traducir
     * @return Devuelve las consultas ya traducidas
     * @throws Exception Excepción de error
     */
    public HashMap<String, String> getConsultasTraducidas(String consultaSQL) throws Exception {

        //Obtenemos la consulta sin asteriscos en el caso de que lo hubiera. Ej. SELECT * FROM Tabla, ....
        consultaSQL = obtenerConsultaSinASterisco(consultaSQL);

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