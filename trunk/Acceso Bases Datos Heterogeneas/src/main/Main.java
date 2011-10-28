package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bárez
 * Clase principal del programa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Diccionario miDiccionario = new Diccionario();
        String mensaje = new String();
        String palabra = new String();
        
        System.out.println("Introduzca una expresion:");
        
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        try {palabra = br.readLine();}
        catch (IOException ex) {Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);}
        
        ArrayList miArray = miDiccionario.desmembrar(palabra);
        System.out.println("Tamaño del array(comprobación): " + miArray.size() + "\n");
        for (int i=0; i<miArray.size(); i++)
        {            
            System.out.println(((Tupla)miArray.get(i)).getPalabra() + "-> " + ((Tupla)miArray.get(i)).isEsReservada());
        }
    }
}