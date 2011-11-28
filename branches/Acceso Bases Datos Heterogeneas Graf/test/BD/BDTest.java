/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

import java.util.HashMap;
import Utilidades.Traductor;
import XML.Configuracion;
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
        Configuracion configuracion = new Configuracion();

        int numBD = configuracion.numBaseDatos();

        for(int i=0; i<numBD; i++) {
            String nombreBD = configuracion.getNombreBaseDatos(i);

            System.out.println("Abriendo conexión con " + nombreBD);
            String cadenaConexion = configuracion.getValor(nombreBD, "conexion");
            String usuario = configuracion.getValor(nombreBD, "usuario");
            String contraseña = configuracion.getValor(nombreBD, "password");
            instance.abrirBD(cadenaConexion, usuario, contraseña);

            System.out.println("Cerrando conexión con " + nombreBD);
            instance.cerrar();
        }
    }

    /**
     * Test of consultar method, of class BD.
     */
    @Test
    public void testConsultar() throws Exception {
        //Creamos un objeto BD
        BD instance = new BD();
        Configuracion configuracion = new Configuracion();
        Traductor traductor = new Traductor();

        String consultaGenerica = "SELECT substr(titulo,1,10), fechaEdicion, precio, nombreAutor FROM"
                + " Libro, Autor WHERE Libro.idAutor = Autor.idAutor";

        HashMap<String, String> consultas = traductor.getConsultasTraducidas(consultaGenerica);

        int numBD = configuracion.numBaseDatos();

        for(int i=0; i<numBD; i++) {
            String nombreBD = configuracion.getNombreBaseDatos(i);

            System.out.println("Comprobando consulta a " + nombreBD);
            String cadenaConexion = configuracion.getValor(nombreBD, "conexion");
            String usuario = configuracion.getValor(nombreBD, "usuario");
            String contraseña = configuracion.getValor(nombreBD, "password");
            instance.abrirBD(cadenaConexion, usuario, contraseña);

            Connection conexion = DriverManager.getConnection (cadenaConexion,usuario, contraseña);
            Statement myStatement = conexion.createStatement();

            ResultSet result = instance.consultar(consultas.get(nombreBD));
            ResultSet expResult = myStatement.executeQuery (consultas.get(nombreBD)); //ResultSet experado

            coomprobarResultSet(result, expResult);

            instance.cerrar();
        }
    }

    private void coomprobarResultSet(ResultSet result, ResultSet expResult) throws SQLException {
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
    }
}
