package main;

import BD.BaseDatos;

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

    }

    /**
     * Función principal del programa
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Main main = new Main();
    }
}
