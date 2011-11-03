package main;
import Utilidades.LecturaTeclado;
import java.util.ArrayList;

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
        //SELECT DE PRUEBA, por ej.
        //SELECT idLibro, titulo, ISBN, paginas, stock, fechaEdicion FROM Libro;
        String consultaSQL = lectura.leetTexto();

        ArrayList<String> traducciones = traductor.getConsultasTraducidas(consultaSQL);

        for(int i=0; i<traducciones.size(); i++)
            System.out.println(traducciones.get(i));

        /*
         * Ahora faltaría crear una clase BD [la cula emplea la clase Configuracion para realizar las conexiones, y segun el tipo de conexion
         * tendria que ejecutarle un sql del traductor u otro [traducciones pues cambiar que devuelva un hashmap (clave => basedatos, valor => sql) por ej.
         *
         * Analizar si meter el traductor dentro de bd o en el main mandando las traducciones a la bd.
         *
         * Despues de esto ya mirar patrones y mierdas para no violar nada y cerrar cualquier bug. y listo, documentar funciones
         * hacer junits y mierdas, y mandarlo a mariano y a por la siguiente iteracion
         *
         * [usar regex? analizar, si no ya empezar con version grafica sencilla por ej.]
         *
         * ¿Que te parece jaime? Estas son mis ideas que he sacado a lo largo del día
         */
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Main principal = new Main();
    }
}
