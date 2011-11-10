package main;
import BD.Consultor;
import Utilidades.LecturaTeclado;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jaime Bárez y Miguel González
 * Clase principal del programa
 */
public class Main {
    //
    LecturaTeclado lectura;
    Traductor traductor;

    public Main() {
        traductor = new Traductor();
        lectura = new LecturaTeclado();
        
        System.out.println("Escribe la consulta SQL:");
        //SELECT DE PRUEBA, por ej.
        //SELECT idLibro, titulo, ISBN, paginas, stock, fechaEdicion FROM Libro;
        String consultaSQL = lectura.leerTexto();

        HashMap<String, String> traducciones = traductor.getConsultasTraducidas(consultaSQL);
        Consultor consultor = new Consultor();
        ArrayList<String> resultados = consultor.lanzarConsulta(traducciones);

        for(int i=0; i<resultados.size(); i++)
            System.out.println(resultados.get(i));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Main principal = new Main();
    }
}
