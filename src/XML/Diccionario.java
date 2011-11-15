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
        super("traducciones.xml", "basedatos", "tabla");
    }

    //Corregida
    public String getTraduccionPalabra(String idioma, String tabla, String palabra) {
        //Obtenemos el HashMap de las tablas del idioma
        HashMap<String,Object> traduccionesTablas = (HashMap<String,Object>) xmlLeido.get(idioma.toUpperCase());
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

    //Automáticamente intenta traducir la palabra
    //Busca la palabra en las distintas tablas de un idioma
    //O bien la palabra indica la base de datos, ej. LIBRO.IDAUTOR
    public String getTraduccionPalabraAutomatica(String idioma, String palabra) {
        //Obtenemos el HashMap de las tablas del idioma
        HashMap<String,Object> traduccionesBD = (HashMap<String,Object>) xmlLeido.get(idioma.toUpperCase());
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
                    //Obtenemos la traducción de la palabra en esa tabla
                    traduccionObtenida = (String)traduccionesTabla.get(palabra.toUpperCase()); //Si no se encuentra la palabra en la tabla del diccionario
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

    public String getTraduccionPalabraAutomaticaInversa(String idioma, String palabra) {
        //Obtenemos el HashMap de las tablas del idioma
        HashMap<String,Object> traduccionesBD = (HashMap<String,Object>) xmlLeido.get(idioma.toUpperCase());
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
            //Si no se ha encontrado la palabra
            if(!encontrada) {
                return palabra; //Devolvemos la palabra tal cual la escribió el usuario
            } else {
                return traduccionObtenida;
            }
        }
    }

    //Corregido
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

    //Corregido
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

    //Corregido
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

    //Corregido
    public int numDiccionarios() {
        return xmlLeido.size();
    }

    //Corregido
    public int numTablasDiccionario(HashMap<String, Object> tablasDiccionario) {
        return tablasDiccionario.size();
    }

    //Corregido
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

    //Corregido
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

    //Corregido
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