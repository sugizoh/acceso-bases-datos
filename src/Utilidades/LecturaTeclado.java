package Utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase que se encarga de la lectura de teclado
 * @author Miguel González y Jaime Bárez
 */
public class LecturaTeclado
{   /**
     * Lee un texto
     * @return El texto leído
     */
    public String leerTexto() {
        String palabra = null;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        try {
            palabra = br.readLine();}
        catch (IOException ex) {
            Logger.getLogger(LecturaTeclado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return palabra;
    }
}
