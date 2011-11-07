/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import Utilidades.Analizador;
import Utilidades.Tupla;
import XML.Diccionario;
import java.util.ArrayList;

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

    public ArrayList<String> getConsultasTraducidas(String consultaSQL) {
        ArrayList<String> consultasTraducidas = new ArrayList<String>();

        ArrayList<Tupla> consultaTroceada = analizador.desmembrar(consultaSQL);

        //Recorremos todos los diccionarios y vamos creando las consultas traducidas
        for(int i=0; i<diccionario.numDiccionarios(); i++) {
            String cadenaSqlTraducida = "";
            for(int j=0; j<consultaTroceada.size(); j++) {
                if(consultaTroceada.get(j).isEsSeparador()) { //Si es reservada no hacemos nada
                    cadenaSqlTraducida += consultaTroceada.get(j).getPalabra();
                } else { //Si no lo es
                    cadenaSqlTraducida += diccionario.getTraduccionPalabra(diccionario.getNombreDiccionario(i),consultaTroceada.get(j).getPalabra());
                }
            }
            consultasTraducidas.add(cadenaSqlTraducida);
        }

        return consultasTraducidas;
    }
}