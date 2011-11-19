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

    private String obtenerConsultaSinASterisco(String consultaSQL)
    {
        //Preanalizamos el sql para sustituir el * por los nombres de las columnas
        String regexAsterisco = "select[ ]+\\*[ ]+from |select[ ]+\\*[ ]*,"; //Ej. select * from tabla .... Ò select *, .... , ...

        Pattern patronAsterisco = Pattern.compile(regexAsterisco, Pattern.CASE_INSENSITIVE); //Compilamos el patrón
        Matcher matcherAsterisco = patronAsterisco.matcher(consultaSQL);

        if(matcherAsterisco.find()) //Si hay una coincidencia
        {
            //Sustituimos el primer asterisco por las columnas
            consultaSQL = consultaSQL.replaceFirst(("\\*"), obtenerColumnasRemplazarAsterisco(consultaSQL));
        }


        //Creo un ArrayList que contendrá las posiciones del asterisco
        ArrayList<Integer> posicionesAsteriscoReemplazar = new ArrayList<Integer>();

        //Creamos el HashMap que contendrá las consultas traducidas
        HashMap<String, String> consultasTraducidas = new HashMap<String, String>();
        //Analizamos el sql
        ArrayList<Tupla> consultaTroceada = analizador.desmembrar(consultaSQL);

        //Buscamos el asterisco entre columnas. ej. Select ..., ..., *, ... , ... FROM ...
        if(posicionesAsteriscoReemplazar.isEmpty())
        {
            //Recorremos toda la consulta troceada buscando una columna que cumpla un patrón ",[ ]*\\*[ ]*," y no esté dentro de una función
            String regexAsteriscoEntreColumna = ",[ ]*\\*[ ]*";
            Pattern patron = Pattern.compile(regexAsteriscoEntreColumna, Pattern.CASE_INSENSITIVE); //Compilamos el patrón

            int contadorLongitudSQL = 0; //Necesitamos sumar las longitudes de los trozos para tener la longitud en la que estaríamos si fuera una cadena
            String pilaParentesisApertura = ""; //Vamos apilando los paréntesis que habren una función
            for(int i=0; i<consultaTroceada.size(); i++) {
                String trozo = consultaTroceada.get(i).getPalabra();

                if(trozo.startsWith("("))
                {
                    pilaParentesisApertura += "(";
                } else {
                    if(!pilaParentesisApertura.isEmpty()) { //Si la pila no está vacía
                        //Estamos dentro de una función
                        if(trozo.contains(")")) { //Si es un cierre de paréntesis
                            pilaParentesisApertura = pilaParentesisApertura.substring(0, pilaParentesisApertura.length() - 1); //Desapilamos
                        }
                    } else { //Si la pila está vacía

                        Matcher encaja = patron.matcher(trozo);

                        while(encaja.find()) //Mientras encontrado una columna ", *,"
                        {
                            int asteriscoEn = trozo.indexOf('*', encaja.start()) + contadorLongitudSQL;
                            posicionesAsteriscoReemplazar.add(asteriscoEn);
                        }
                    }
                }

                contadorLongitudSQL += trozo.length(); //Sumamos la longitud del trozo
            }
        }

        if(posicionesAsteriscoReemplazar.size() == 1) //Si solo hay un asterisco
        {
            String nuevaConsultaSQL = consultaSQL.substring(0, posicionesAsteriscoReemplazar.get(0));
            nuevaConsultaSQL += obtenerColumnasRemplazarAsterisco(consultaSQL);
            nuevaConsultaSQL += consultaSQL.substring(posicionesAsteriscoReemplazar.get(0) + 1);

            consultaSQL = nuevaConsultaSQL; //Reemplazamos por la nueva consulta SQL

            consultaTroceada = analizador.desmembrar(consultaSQL); //Volvemos a trocear la consulta
        }

        return consultaSQL;
    }

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