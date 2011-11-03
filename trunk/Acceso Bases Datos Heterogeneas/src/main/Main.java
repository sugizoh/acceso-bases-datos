package main;
import BD.BaseDatos_old;
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
 * @author Bárez        miDiccionario.
 * Clase principal del programa
 */
public class Main {

    private Connection conexionBD;
    private Statement stat;
    private ResultSet rs;
    public Main() {

        Diccionario xml = new Diccionario();
        System.out.println("La traducción de titulo es: " + xml.getTraduccionPalabra("titulo"));

        try
        {
            Class.forName("org.sqlite.JDBC");

            //Abrimos una conexión con la base de datos
            conexionBD = DriverManager.getConnection("jdbc:sqlite:BDLibros.db");

            stat = conexionBD.createStatement();
            /*rs = stat.executeQuery("select * from Amazon;");*/
            /*rs.close();
             * v
             */conexionBD.close();

            //Creamos el objeto para manejar la base de datos de amazon
            /*bdAmazo        miDiccionario.n = new BaseDatosAmazon(conexionBD);*/
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseDatos_old.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException ex) {
            Logger.getLogger(BaseDatos_old.class.getName()).log(Level.SEVERE, null, ex);
        }

        Analizador miAnalizador = new Analizador();
        String mensaje = new String();
        String palabra = new String();

        System.out.println("Introduzca una expresion:");


        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        try {palabra = br.readLine();}
        catch (IOException ex) {Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);}

        ArrayList miArray = miAnalizador.desmembrar(palabra);
        System.out.println("Tamaño del array(comprobación): " + miArray.size() + "\n");
        for (int i=0; i<miArray.size(); i++)
        {
            System.out.println(((Tupla)miArray.get(i)).getPalabra() + "-> " + ((Tupla)miArray.get(i)).isEsReservada());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Main principal = new Main();
    }
}
