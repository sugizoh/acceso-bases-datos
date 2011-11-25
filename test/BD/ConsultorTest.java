/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import GUI.ModeloTabla;
import java.sql.ResultSet;
import java.util.HashMap;
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
    
    public ConsultorTest() {
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
    public void testLanzarConsulta() {
        System.out.println("lanzarConsulta");
        HashMap<String, String> sentenciasSQL = null;
        
        String consultaPrueba = new String();
        consultaPrueba = "SELECT substr(titulo,1,10), fechaEdicion, precio, nombreAutor FROM Amazon, Autor WHERE Amazon.idAutor = Autor.idAutor";
        sentenciasSQL.put("amazon", consultaPrueba);
        
        consultaPrueba = "SELECT substr(titulo,1,10), precio, nombreAutor FROM CasaDelLibro, Autor WHERE CasaDelLibro.idAutor = Autor.idAutor";
        sentenciasSQL.put("casadellibro", consultaPrueba);
        
        Consultor instance = new Consultor();
        ModeloTabla expResult = null;
        ModeloTabla result = instance.lanzarConsulta(sentenciasSQL);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of completarTraduccionInversaNombresColumnas method, of class Consultor.
     */
    @Test
    public void testCompletarTraduccionInversaNombresColumnas() {
        System.out.println("completarTraduccionInversaNombresColumnas");
        String[] headTable = null;
        ResultSet rsConsulta = null;
        Consultor instance = new Consultor();
        instance.completarTraduccionInversaNombresColumnas(headTable, rsConsulta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
