package main;

import BD.Consultor;
import Utilidades.LecturaTeclado;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Miguel González y Jaime Bárez
 * Clase principal del programa
 */
public class Main {

    LecturaTeclado lectura;
    Traductor traductor;
    Consultor consultor;

    public Main() {
        traductor = new Traductor();
        lectura = new LecturaTeclado();
        consultor = new Consultor();
        
        System.out.println("Escribe la consulta SQL:");
        //SELECT DE PRUEBA, por ej.
        //SELECT idLibro, titulo, ISBN, paginas, stock, fechaEdicion FROM Libro;
        String consultaSQL = lectura.leerTexto();

        //El primer string nos indica la base de de datos a la que pertenece la consulta. El segundo la consulta ya traducida.
        HashMap<String, String> traducciones = traductor.getConsultasTraducidas(consultaSQL);
        
        //ArrayList con las respuestas del servidor a las consultas lanzadas
        ArrayList<String> resultados = consultor.lanzarConsulta(traducciones);

        for(int i=0; i<resultados.size(); i++)
            System.out.println(resultados.get(i));
    }
    /**
     * Función principal del progama
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Main principal = new Main();
    }
}
