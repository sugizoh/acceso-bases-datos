/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import GUI.ModeloTabla;
import XML.Configuracion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author JaimeInves
 */
public class ConsultorTest {
    
   
    private Statement myStatement;
    private Configuracion configuracion;
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
    public void setUp() throws RuntimeException, SAXException, IOException, ParserConfigurationException {
        configuracion = new Configuracion();
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
        
        
        String [] nombresBasesDatos= {"amazon".toUpperCase(), "casadellibro".toUpperCase()};
        int numBaseDatos = nombresBasesDatos.length;
        
        sentenciasSQL.put("amazon".toUpperCase(), "SELECT substr(tituloLibro,1,10), \"NULL\", precio, nombreAutor FROM Amazon, Autor WHERE Amazon.idAutor = Autor.idAutor");
        sentenciasSQL.put("casadellibro".toUpperCase(), "SELECT substr(titulo,1,10), fechaEdicion, precio, nombreAutor FROM CasaDelLibro, Autor WHERE CasaDelLibro.idAutor = Autor.idAutor");

        
        String datos[][] = null;
        int numColumnas = 0;
        ArrayList<String[]> arrayDatos = new ArrayList<String[]>();
        
        for(int i=numBaseDatos - 1; i>= 0; i--) {
            String nombreBD = nombresBasesDatos[i];
            conexion = DriverManager.getConnection (configuracion.getValor(nombresBasesDatos[i], "conexion"),
                    configuracion.getValor(nombresBasesDatos[i], "usuario"), configuracion.getValor(nombresBasesDatos[i], "password"));
            myStatement = conexion.createStatement();
            ResultSet rsConsulta = myStatement.executeQuery (sentenciasSQL.get(nombreBD));
            
            numColumnas = rsConsulta.getMetaData().getColumnCount();

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


        String headTable[] = new String[4];
        headTable[0] = "substr(TITULO,1,10)";
        headTable[1] = "FECHAEDICION";
        headTable[2] = "PRECIO";
        headTable[3]= "NOMBREAUTOR";
        
        
        ModeloTabla expResult = new ModeloTabla(datos,headTable);
        ModeloTabla result = instance.lanzarConsulta(sentenciasSQL);
        System.out.println("Hemos llegado hasta casi el final");
        
        if(expResult.getColumnCount() == result.getColumnCount())
        {
            if (expResult.getRowCount() == result.getRowCount())
            {
                for (int i=0; i<expResult.getRowCount(); i++)
                {
                    for (int j=0; j<expResult.getColumnCount(); j++)
                     {
                      if(!expResult.getValueAt(i, j).equals(result.getValueAt(i, j)))
                        {
                            fail("Contenido distinto");
                        }
                     }
                }

            }
            else fail("Numero distinto de filas");
        }
        else fail("Numero distinto de columnas");
        
        
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of completarTraduccionInversaNombresColumnas method, of class Consultor.
     */
    @Test
    public void testCompletarTraduccionInversaNombresColumnas() throws Exception {
        System.out.println("completarTraduccionInversaNombresColumnas");
        String headTable[] = new String[4];
        headTable[0] = "\"NULL\"";
        headTable[1] = "\"NULL\"";
        headTable[2] = "\"NULL\"";
        headTable[3]= "\"NULL\"";
        String headTableEsperado[] = new String[4];
        headTableEsperado[0] = "substr(TITULO,1,10)";
        headTableEsperado[1] = "FECHAEDICION";
        headTableEsperado[2] = "PRECIO";
        headTableEsperado[3]= "NOMBREAUTOR";
        
        HashMap<String, String> sentenciasSQL = new HashMap<String, String>();
        
        
        String [] nombresBasesDatos= {"amazon".toUpperCase(), "casadellibro".toUpperCase()};
        int numBaseDatos = nombresBasesDatos.length;
        
        sentenciasSQL.put("amazon".toUpperCase(), "SELECT substr(tituloLibro,1,10), \"NULL\", precio, nombreAutor FROM Amazon, Autor WHERE Amazon.idAutor = Autor.idAutor");
        sentenciasSQL.put("casadellibro".toUpperCase(), "SELECT substr(titulo,1,10), fechaEdicion, precio, nombreAutor FROM CasaDelLibro, Autor WHERE CasaDelLibro.idAutor = Autor.idAutor");
        
        Consultor instance = new Consultor();
        
        
        int numColumnas = 0;
        for(int i=numBaseDatos - 1; i>= 0; i--) {
            String nombreBD = nombresBasesDatos[i];
            conexion = DriverManager.getConnection (configuracion.getValor(nombresBasesDatos[i], "conexion"),
                    configuracion.getValor(nombresBasesDatos[i], "usuario"), configuracion.getValor(nombresBasesDatos[i], "password"));
            myStatement = conexion.createStatement();
            ResultSet rsConsulta = myStatement.executeQuery (sentenciasSQL.get(nombreBD));
            
            instance.completarTraduccionInversaNombresColumnas(headTable, rsConsulta);
            
            //Cerramos la conexión de la base de datos
            conexion.close();
        }
        
        for (int j=0; j<headTable.length; j++)
        {
               if(!headTable[j].equals(headTableEsperado[j]))
               {
                   fail("Contenido distinto");
               }
        }

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}