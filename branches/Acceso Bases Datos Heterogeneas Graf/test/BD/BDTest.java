/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel González y Jaime Bárez
 */
public class BDTest {

    public BDTest() throws SQLException {
        // Se registra el Driver de MySQL
        DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    //Cerramos la conexión una vez terminada las pruebas
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of abrirBD method, of class BD.
     */
    @Test
    public void testAbrirCerrarConexiones() throws Exception {
        //Creamos un objeto BD
        BD instance = new BD();

        System.out.println("Abriendo conexión con Amazon");
        String cadenaConexion = "jdbc:mysql://localhost/Amazon";
        String usuario = "root";
        String contraseña = "sql";
        instance.abrirBD(cadenaConexion, usuario, contraseña);

        System.out.println("Cerrando conexión con amazon");
        instance.cerrar();

        System.out.println("Abriendo conexión con CasaDelLibro");
        cadenaConexion = "jdbc:mysql://localhost/CasaDelLibro";
        instance.abrirBD(cadenaConexion, usuario, contraseña);

        System.out.println("Cerrando conexión con CasaDelLibro");
        instance.cerrar();
    }

    /**
     * Test of consultar method, of class BD.
     */
    @Test
    public void testConsultar() throws Exception {
        BD instance = new BD();

        System.out.println("Comprobando consulta a Amazon");



        //Abrimos la conexión con la base de datos
        String cadenaConexion = "jdbc:mysql://localhost/Amazon";
        String usuario = "root";
        String contraseña = "sql";
        instance.abrirBD(cadenaConexion, usuario, contraseña);

        //Consulta SQL
        String consulta = "SELECT tituloLibro, editorial, numPaginas, nombreAutor FROM Amazon, Autor WHERE Amazon.idAutor = Autor.idAutor;";

        Connection conexion = DriverManager.getConnection (cadenaConexion,usuario, contraseña);
        Statement myStatement = conexion.createStatement();

        ResultSet expResult = myStatement.executeQuery (consulta); //ResultSet experado
        ResultSet result = instance.consultar(consulta); //ResultSet devuelto por BD

        //Comparamos los resultados
        if(expResult.getMetaData().getColumnCount() == result.getMetaData().getColumnCount())
        {
            int numColumnas = result.getMetaData().getColumnCount();

            while (result.next()) {
                if(expResult.next()) {
                for(int j=1; j<=numColumnas; j++) {
                    String valor = result.getString(j).toString();
                    String valorExp = expResult.getString(j).toString();

                    if(!valor.equals(valorExp)) {
                        fail("La consulta devuelve valores distintos");
                    }
                }
                } else {
                    fail("La consulta a Amazon devuelve distinto número de filas");
                }
            }
        } else {
            fail("La consulta a Amazon devuelve distinto número de columnas");
        }

        //Cerramos la conexión con la base de datos
        instance.cerrar();
        conexion.close();

        System.out.println("Comprobando consulta a CasaDelLibro");
        
        //Abrimos la conexión con CasaDelLibro
        cadenaConexion = "jdbc:mysql://localhost/CasaDelLibro";
        instance.abrirBD(cadenaConexion, usuario, contraseña);

        //Consulta SQL
        consulta = "SELECT titulo, ISBN, fechaEdicion, nombreAutor FROM CasaDelLibro, Autor WHERE CasaDelLibro.idAutor = Autor.idAutor;";
        
        conexion = DriverManager.getConnection (cadenaConexion,usuario, contraseña);
        myStatement = conexion.createStatement();

        expResult = myStatement.executeQuery (consulta); //ResultSet experado
        result = instance.consultar(consulta); //ResultSet devuelto por BD

        //Comparamos los resultados
        if(expResult.getMetaData().getColumnCount() == result.getMetaData().getColumnCount())
        {
            int numColumnas = result.getMetaData().getColumnCount();

            while (result.next()) {
                if(expResult.next()) {
                for(int j=1; j<=numColumnas; j++) {
                    String valor = result.getString(j).toString();
                    String valorExp = expResult.getString(j).toString();

                    if(!valor.equals(valorExp)) {
                        fail("La consulta devuelve valores distintos");
                    }
                }
                } else {
                    fail("La consulta a Amazon devuelve distinto número de filas");
                }
            }
        } else {
            fail("La consulta a Amazon devuelve distinto número de columnas");
        }
        
        //Cerramos la conexión con la base de datos
        instance.cerrar();
        conexion.close();
    }
}
