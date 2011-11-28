/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paracaidista
 */
public class ConfiguracionTest {

    public ConfiguracionTest() {
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
     * Test of getValor method, of class Configuracion.
     */
    @Test
    public void testGetValor() throws Exception {
        System.out.println("getValor");
        String baseDatos = "";
        String valor = "";
        Configuracion instance = new Configuracion();
        String expResult = "";
        String result = instance.getValor(baseDatos, valor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBaseDatos method, of class Configuracion.
     */
    @Test
    public void testGetBaseDatos() throws Exception {
        System.out.println("getBaseDatos");
        Configuracion instance = new Configuracion();
        ArrayList expResult = null;
        ArrayList result = instance.getBaseDatos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numBaseDatos method, of class Configuracion.
     */
    @Test
    public void testNumBaseDatos() throws Exception {
        System.out.println("numBaseDatos");
        Configuracion instance = new Configuracion();
        int expResult = 0;
        int result = instance.numBaseDatos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreBaseDatos method, of class Configuracion.
     */
    @Test
    public void testGetNombreBaseDatos() throws Exception {
        System.out.println("getNombreBaseDatos");
        int posicion = 0;
        Configuracion instance = new Configuracion();
        String expResult = "";
        String result = instance.getNombreBaseDatos(posicion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}