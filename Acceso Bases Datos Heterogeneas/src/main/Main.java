package main;

import BD.BaseDatos;
import java.util.ArrayList;

/**
 *
 * @author Jaime Bárez y Miguel González
 */
public class Main
{
    BaseDatos bd;

    /*
     * Constructor del objeto Main
     */
    public Main()
    {
        //Creamos un objeto para manejar la base de datos
        bd = new BaseDatos();

        //Vamos a imprimir una consulta a la base de datos de amazon
        String sqlAmazon = "SELECT tituloLibro, idAutor, numPaginas, enStock FROM `Amazon` WHERE numPaginas > 400;";

        ArrayList<String> arrayConsulta = bd.consultaLibros(sqlAmazon);

        for(int i=0; i<arrayConsulta.size(); i++)
            System.out.println(arrayConsulta.get(i));
    }

    /**
     * Función principal del programa
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Main main = new Main();
    }
}
