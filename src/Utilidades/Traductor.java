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

    public HashMap<String, String> getConsultasTraducidas(String consultaSQL) {
        HashMap<String, String> consultasTraducidas = new HashMap<String, String>();

        ArrayList<Tupla> consultaTroceada = analizador.desmembrar(consultaSQL);

        //Recorremos todos los diccionarios y vamos creando las consultas traducidas
        for(int i=0; i<diccionario.numDiccionarios(); i++) {
            String cadenaSqlTraducida = "";
            String nombreDiccionario = diccionario.getNombreDiccionario(i);
            for(int j=0; j<consultaTroceada.size(); j++) {
                if(consultaTroceada.get(j).isEsSeparador()) { //Si es reservada no hacemos nada
                    cadenaSqlTraducida += consultaTroceada.get(j).getPalabra();
                } else { //Si no lo es
                    cadenaSqlTraducida += diccionario.getTraduccionPalabra(nombreDiccionario,consultaTroceada.get(j).getPalabra());
                }
            }
            consultasTraducidas.put(nombreDiccionario, cadenaSqlTraducida);
            
        }

        return consultasTraducidas;
    }
}