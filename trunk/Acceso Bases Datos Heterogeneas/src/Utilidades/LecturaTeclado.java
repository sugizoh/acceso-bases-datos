/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel González y Jaime Bárez
 */
public class LecturaTeclado
{
    public String leetTexto() {
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
