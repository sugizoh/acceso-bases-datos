package main;
import Utilidades.Tupla;
import Utilidades.Analizador;
import BD.BaseDatos_old;
import Utilidades.LecturaTeclado;
import XML.Configuracion;
import XML.Diccionario;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaime Bárez y Miguel González
 * Clase principal del programa
 */
public class Main {

    LecturaTeclado lectura;
    Traductor traductor;

    public Main() {
        
        traductor = new Traductor();
        lectura = new LecturaTeclado();
        
        System.out.println("Escribe la consulta SQL:");
        String consultaSQL = lectura.leetTexto();

        ArrayList<String> traducciones = traductor.getConsultasTraducidas(consultaSQL);

        for(int i=0; i<traducciones.size(); i++)
            System.out.println(traducciones.get(i));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Main principal = new Main();
    }
}
