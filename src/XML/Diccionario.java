/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML;

import Utilidades.XML;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Miguel González y Jaime Bárez
 * Clase Diccionario
 */
public class Diccionario extends XML
{
    /**
     * Constructor de la clase Diccionario
     */
    public Diccionario() throws RuntimeException, SAXException, IOException, ParserConfigurationException
    {
        super("traducciones.xml", "basedatos", "tabla");
    }

    /**
     * Devuelve la traducción de una palabra para una base de datos y una tabla
     *
     * @param baseDatos Base de Datos a la que se traducirá la palabra
     * @param tabla Tabla a la que se va a traducir la palabra
     * @param palabra Palabra a traducir
     * @return Devuelve la palabra traducida para una base de datos y una tabla
     */
    public String getTraduccionPalabra(String baseDatos, String tabla, String palabra) {
        //Obtenemos el HashMap de las tablas del idioma
        HashMap<String,Object> traduccionesTablas = (HashMap<String,Object>) xmlLeido.get(baseDatos.toUpperCase());
        //Si no existe la base de datos
        if(traduccionesTablas == null) {
            return palabra; //Devolvemos la palabra tal cual la escribió el usuario
        } else {
            //Obtenemos las traducciones para la tabla indicada
            HashMap<String, String> traduccionesTabla = (HashMap<String,String>) traduccionesTablas.get(tabla.toUpperCase());
            //Si no existe la tabla
            if(traduccionesTabla == null) {
                return palabra; //Devolvemos la palabra tal cual la escribió el usuario
            } else {
                //Obtenemos la traducción de la palabra en esa tabla
                String traduccionObtenida = (String)traduccionesTabla.get(palabra.toUpperCase()); //Si no se encuentra la palabra en la tabla del diccionario
                //Si no existe la palabra
                if(traduccionObtenida == null) {
                    return palabra; //Devolvemos la palabra tal cual la escribió el usuario
                } else {
                    return traduccionObtenida;
                }
            }
        }
    }

    /**
     * Obtenemos la palabra traducida para una base de datos
     *
     * @param diccionario Base de datos en la cual se va a buscar
     * @param palabra Palabra a traducir
     * @return Devuelve la palabra traducida
     */
    public String getTraduccionPalabraAutomatica(String diccionario, String palabra) {
        //Obtenemos el HashMap de las tablas del idioma
        HashMap<String,Object> traduccionesBD = (HashMap<String,Object>) xmlLeido.get(diccionario.toUpperCase());
        //Si no existe la base de datos
        if(traduccionesBD == null) {
            return palabra; //Devolvemos la palabra tal cual la escribió el usuario
        } else {
            //Obtenemos las tablas de la primera base de datos (nos da igual cual pues vamos a ir a las columnas que son iguales en todas)
            ArrayList<String> tablas = getTablasDiccionario();
            //Recorremos las tablas buscando la traducción
            boolean encontrada = false;
            String traduccionObtenida = null;
            //Buscamos hasta encontrarla en la primera traducción que haya (Si hay más traducciones se supone que si el esquema es igual
            //La traducción es igual, el gestor se encargará de decir si se hace a un FROM de varias tablas de decir
            //Si es ambigua la columna
            for(int i=0; i<tablas.size() && !encontrada; i++) {
                //Obtenemos las traducciones para la tabla indicada
                HashMap<String, String> traduccionesTabla = (HashMap<String,String>) traduccionesBD.get(tablas.get(i).toUpperCase());
                //Si existe la tabla
                if(traduccionesTabla != null) {
                    //Comprobamos si es `palabra`
                    if(palabra.startsWith("`") && palabra.endsWith("`"))
                    {
                        palabra = palabra.substring(1, palabra.length() - 1);
                        //Obtenemos la traducción de la palabra en esa tabla
                        traduccionObtenida = (String)traduccionesTabla.get(palabra.toUpperCase()); //Si no se encuentra la palabra en la tabla del diccionario
                        palabra = "`" + palabra + "`";
                    } else {
                        //Obtenemos la traducción de la palabra en esa tabla
                        traduccionObtenida = (String)traduccionesTabla.get(palabra.toUpperCase()); //Si no se encuentra la palabra en la tabla del diccionario
                    }
                    //Si no existe la palabra
                    if(traduccionObtenida != null) {
                        encontrada = true;
                    }
                }
            }
            //Si no se ha encontrado la palabra
            if(!encontrada) {
                return palabra; //Devolvemos la palabra tal cual la escribió el usuario
            } else {
                return traduccionObtenida;
            }
        }
    }

    /**
     * Función que devuelve la traducción al esquema genérico de una palabra
     * 
     * @param palabra Palabra traducida de la cual se quiere conocer como se llama en el esquema genérico
     * @return Devueve la palabra en el esquema genérico
     */
    public String getTraduccionPalabraAutomaticaInversa(String palabra) {
        ArrayList<String> nombresBD = getNombresDiccionarios();

        String traduccionObtenida = null;
        boolean encontrada = false;

        for(int z=0; z<nombresBD.size() && !encontrada; z++)
        {
            String nombreBD = nombresBD.get(z);

            //Obtenemos el HashMap de las tablas del idioma
            HashMap<String,Object> traduccionesBD = (HashMap<String,Object>) xmlLeido.get(nombreBD.toUpperCase());

            if(traduccionesBD != null) {
                //Obtenemos las tablas de la primera base de datos (nos da igual cual pues vamos a ir a las columnas que son iguales en todas)
                ArrayList<String> tablas = getTablasDiccionario();
                //Recorremos las tablas buscando la traducción
                encontrada = false;
                traduccionObtenida = null;
                //Buscamos hasta encontrarla en la primera traducción que haya (Si hay más traducciones se supone que si el esquema es igual
                //La traducción es igual, el gestor se encargará de decir si se hace a un FROM de varias tablas de decir
                //Si es ambigua la columna
                for(int i=0; i<tablas.size() && !encontrada; i++) {
                    //Obtenemos las traducciones para la tabla indicada
                    HashMap<String, String> traduccionesTabla = (HashMap<String,String>) traduccionesBD.get(tablas.get(i).toUpperCase());
                    //Si existe la tabla
                    if(traduccionesTabla != null) {
                        //Iteramos las traducciones hasta encontar el mismo valor, entonces devolvemos la clave
                        Iterator it = traduccionesTabla.entrySet().iterator();
                        while (it.hasNext() && !encontrada) {
                            Map.Entry e = (Map.Entry)it.next();
                            if(palabra.toUpperCase().equals((e.getValue().toString().toUpperCase()))) {
                                traduccionObtenida = e.getKey().toString();
                                encontrada = true;
                            }
                        }
                    }
                }

            }
        }

        //Si no se ha encontrado la palabra
        if(!encontrada) {
            return palabra; //Devolvemos la palabra tal cual la escribió el usuario
        } else {
            return traduccionObtenida;
        }
    }

    /**
     * Función que develve el nombre de los diccionarios
     *
     * @return Devuelve el nombre de los diccionaros
     */
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

    /**
     * Función que devuelve el nombre de las tablas del diccionario
     *
     * @return Devuelve el nombre de las tablas del diccionario
     */
    public ArrayList<String> getTablasDiccionario() {
        //Cogemos el primer diccionario ya que todos van a tener las mismas claves
        HashMap<String, Object> diccionario = (HashMap<String, Object>)xmlLeido.get(getNombreDiccionario(0));
        //Creamos un ArrayList que contendrá el nombre de las tablas
        ArrayList<String> tablas = new ArrayList<String>();
        //Iteramos obteniendo las claves
        Iterator it = diccionario.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            tablas.add(e.getKey().toString());
        }

        return tablas;
    }

    /**
     * Función que devuelve las columnas de una base de datos
     *
     * @return HashMap con el nombre de las columnas se la base de datos por tablas
     */
    public HashMap<String, ArrayList<String>> getColumnasBaseDatos() {
        //HashMap que contendrá las tablas para cada base de datos
        HashMap<String, ArrayList<String>> columnas = new HashMap<String, ArrayList<String>>();
        //Sacamos las tablas que contendrán los diccionarios
        ArrayList<String> tablasDiccionario = getTablasDiccionario();
        //Recorremos las bases de datos
        for(int i=0; i<numDiccionarios(); i++) {
            //Recorremos las tablas del diccionario
            for(int j=0; j<tablasDiccionario.size(); j++) {
                //Obtenemos el nombre de la tabla actual
                String tablaActual = getNombreTablaDiccionario((HashMap<String,Object>)xmlLeido.get(getNombreDiccionario(i)), j);
                //Obtenemos la tabla actual
                HashMap<String, String> tabla = (HashMap<String, String>)((HashMap<String,Object>)xmlLeido.get(getNombreDiccionario(i))).get(tablaActual.toUpperCase());
                //Creamos un ArrayList con las claves
                ArrayList<String> claves = new ArrayList<String>();
                //Iteramos sobre la tablas para obtener los nombres de columna (key)
                Iterator it = tabla.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry e = (Map.Entry)it.next();
                    //Obtenemos la clave
                    String claveObtenida = e.getKey().toString();
                    //Comprobamos que no es el nombre de una base de datos
                    boolean esBD = false;
                    for(int k=0; k<tablasDiccionario.size() && !esBD; k++) {
                        if(tablasDiccionario.get(j).toUpperCase().equals(claveObtenida.toUpperCase())) {
                            esBD = true;
                        }
                    }
                    if(!esBD) {
                        claves.add(e.getKey().toString());
                    }
                }
                //Agregamos a la tabla actual las claves
                columnas.put(tablaActual.toUpperCase(), claves);
            }
        }

        return columnas;
    }

    /**
     * Función que devuelve el número de diccionarios
     *
     * @return Devuelve el número de diccionarios
     */
    public int numDiccionarios() {
        return xmlLeido.size();
    }

    /**
     * Función que devuelve el número de tablas de un diccionario
     *
     * @param Diccionario del cual se devolverá el número de tablas
     * @return Devuelve el número de tablas
     */
    public int numTablasDiccionario(HashMap<String, Object> tablasDiccionario) {
        return tablasDiccionario.size();
    }

    /**
     * Función que devuelve el nombre de un diccionario en una posición indicada
     *
     * @param posicion Posición del diccionario
     * @return Devuelve el nombre del diccionario
     */
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

        return hashCode.toUpperCase();
    }

    /**
     * Función que devuelve el nombre de una tabla indicada en una posición de un diccionario.
     *
     * @param tablasDiccionario Diccionario en cuya posición se quiere obtener el nombre de la tabla
     * @param posicion Posición de la tabla en el diccionario
     * @return Devuelve el nombre de una tabla
     */
    public String getNombreTablaDiccionario(HashMap<String, Object> tablasDiccionario, int posicion) {
        //Iteramos para obtener la clave en la posición iterada
        int posActual = 0;
        String hashCode = null;
        Iterator it = tablasDiccionario.entrySet().iterator();
        while (it.hasNext() && posActual <= posicion) {
            posActual++;
            Map.Entry e = (Map.Entry)it.next();
            hashCode = e.getKey().toString();
        }

        return hashCode.toUpperCase();
    }

    /**
     * Función que analiza si una clave es una clave ambigua o no (es decir, está en varias tablas o no)
     *
     * @param clave Clave de una columna
     * @return Devuelve verdad si la clave es ambigua, sino false
     */
    public boolean claveAmbigua(String clave) {
        //Obtenemos el HashMap de las tablas de la primera base de datos (el esquema es igual para todas las bases de datos)
        HashMap<String,Object> traduccionesBD = (HashMap<String,Object>) xmlLeido.get(getNombreDiccionario(0));
        //Sacamos las tablas que contendrán los diccionarios
        ArrayList<String> tablas = getTablasDiccionario();

        boolean encontrada = false;
        boolean ambigua = false;
        for(int i=0; i<tablas.size() && !ambigua; i++) {
            //Vamos a traducir la palabra en la tabla (si se traduce dos veces, clave ambigua)
            String traduccionObtenida = null;
            //Obtenemos las traducciones para la tabla indicada
            HashMap<String, String> traduccionesTabla = (HashMap<String,String>) traduccionesBD.get(tablas.get(i).toUpperCase());
            //Si existe la tabla
            if(traduccionesTabla != null) {
                //Obtenemos la traducción de la palabra en esa tabla
                traduccionObtenida = (String)traduccionesTabla.get(clave.toUpperCase()); //Si no se encuentra la palabra en la tabla del diccionario
                //Si no existe la palabra
                if(traduccionObtenida != null && !encontrada) {
                    encontrada = true;
                } else {
                    if(traduccionObtenida != null && encontrada) {
                        ambigua = true;
                    }
                }
            }
       }
       return ambigua;
    }
}