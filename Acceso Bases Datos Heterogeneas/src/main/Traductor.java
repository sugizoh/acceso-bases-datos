package main;

import Utilidades.Analizador;
import Utilidades.Tupla;
import XML.Diccionario;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Miguel González y Jaime Bárez
 * Clase encargada de las traducciones
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

    public HashMap<String, String> getConsultasTraducidas(String consultaSQL) {
        
        //El primer string nos indica la base de de datos a la que pertenece la consulta. El segundo la consulta traducida.
        HashMap<String, String> consultasTraducidas = new HashMap<String, String>();

        //ArrayList de tuplas tras desmembrar la frase. Cada tupla contiene una palabra y un boolean que indica si es separador.
        ArrayList<Tupla> consultaTroceada = analizador.desmembrar(consultaSQL);

        //Recorremos todos los diccionarios y vamos creando las consultas traducidas
        for(int i=0; i<diccionario.numDiccionarios(); i++) {
            
            String cadenaSqlTraducida = "";
            String nombreDiccionario = diccionario.getNombreDiccionario(i);
            
            for(int j=0; j<consultaTroceada.size(); j++) {
                
                if(consultaTroceada.get(j).isEsSeparador()) { //Si es reservada la añadimos sin traducir
                    cadenaSqlTraducida += consultaTroceada.get(j).getPalabra();
                } else { //Si no lo es, añadimos la palabra traducida
                    cadenaSqlTraducida += diccionario.getTraduccionPalabra(nombreDiccionario,consultaTroceada.get(j).getPalabra());
                }
            }
            //Añadimos la entrada al hashmap
            consultasTraducidas.put(nombreDiccionario, cadenaSqlTraducida);
            
        }

        return consultasTraducidas;
    }
}