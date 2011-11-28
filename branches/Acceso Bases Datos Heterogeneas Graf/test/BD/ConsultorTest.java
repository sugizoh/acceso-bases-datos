/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import GUI.ModeloTabla;
import java.util.ArrayList;
import java.util.HashMap;
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
 * @author JaimeInves
 */
public class ConsultorTest {
    
   
    private Statement myStatement;
    public String cadenaConexionAmazon = "jdbc:mysql://localhost/Amazon";
    public String cadenaConexionCasaDelLibro = "jdbc:mysql://localhost/CasaDelLibro";
    public String[] cadenaConexiones= {cadenaConexionAmazon, cadenaConexionCasaDelLibro};
    public String usuario = "root";
    public String contraseña = "sql";
    private Connection conexion;
    
    public ConsultorTest() throws SQLException {
        DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
        System.out.println("Abriendo conexión con Amazon");
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }

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
     * Test of lanzarConsulta method, of class Consultor.
     */
    @Test
    public void testLanzarConsulta() throws Exception {
        System.out.println("lanzarConsulta");
        Consultor instance = new Consultor();
        HashMap<String, String> sentenciasSQL = new HashMap<String, String>();
        
        
        String [] nombresBasesDatos= {"amazon", "casadellibro"};
        int numBaseDatos = 2;
        
        sentenciasSQL.put("amazon", "SELECT substr(tituloLibro,1,10), precio, nombreAutor FROM Amazon, Autor WHERE Amazon.idAutor = Autor.idAutor");
        sentenciasSQL.put("casadellibro", "SELECT substr(titulo,1,10), fechaEdicion, precio, nombreAutor FROM CasaDelLibro, Autor WHERE CasaDelLibro.idAutor = Autor.idAutor");

        
        
        
        String headTable[] = null;
        String datos[][] = null;
        int numColumnas = 0;
        ArrayList<String[]> arrayDatos = new ArrayList<String[]>();
        
        for(int i=0; i<numBaseDatos; i++) {
            String nombreBD = nombresBasesDatos[i];
            conexion = DriverManager.getConnection (cadenaConexiones[i],usuario, contraseña);
            myStatement = conexion.createStatement();
            ResultSet rsConsulta = myStatement.executeQuery (sentenciasSQL.get(nombreBD));
            
            numColumnas = rsConsulta.getMetaData().getColumnCount();

            //En la primera base de datos obtenemos los datos de la cabecera de la tabla
            if(i == 0) {
                headTable = new String[numColumnas];
                for(int j=0; j<numColumnas; j++) {
                    headTable[j] = new String();
                }
            }

            //Traducimos la headTable
            instance.completarTraduccionInversaNombresColumnas(headTable, rsConsulta);

            int consultaActual = 0;
            while (rsConsulta.next()) {
                String []filaDatos = new String[numColumnas];
                for(int j=1; j<=numColumnas; j++) {
                    String valor = rsConsulta.getString(j).toString();
                    filaDatos[j-1] = valor;
                }
                arrayDatos.add(filaDatos);
                consultaActual++;
            }
            //Cerramos la conexión de la base de datos
            conexion.close();
        }

        datos = new String[arrayDatos.size()][numColumnas];

        for(int i=0; i<arrayDatos.size(); i++)
            datos[i] = arrayDatos.get(i);


        
        
        
        ModeloTabla expResult = new ModeloTabla(datos,headTable);
        ModeloTabla result = instance.lanzarConsulta(sentenciasSQL);
        System.out.println("Hemos llegado hasta casi el final");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of completarTraduccionInversaNombresColumnas method, of class Consultor.
     */
    @Test
    public void testCompletarTraduccionInversaNombresColumnas() throws Exception {
        System.out.println("completarTraduccionInversaNombresColumnas");
        String[] headTable = null;
        ResultSet rsConsulta = null;
        Consultor instance = new Consultor();
        instance.completarTraduccionInversaNombresColumnas(headTable, rsConsulta);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
