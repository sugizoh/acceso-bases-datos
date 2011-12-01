/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.util.ArrayList;
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
public class DiccionarioTest {
    
    public DiccionarioTest() {
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
     * Test of getTraduccionPalabra method, of class Diccionario.
     */
    @Test
    public void testGetTraduccionPalabra() {
        System.out.println("getTraduccionPalabra");
        String baseDatos = "";
        String tabla = "";
        String palabra = "";
        Diccionario instance = new Diccionario();
        String expResult = "";
        String result = instance.getTraduccionPalabra(baseDatos, tabla, palabra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTraduccionPalabraAutomatica method, of class Diccionario.
     */
    @Test
    public void testGetTraduccionPalabraAutomatica() {
        System.out.println("getTraduccionPalabraAutomatica");
        String diccionario = "";
        String palabra = "";
        Diccionario instance = new Diccionario();
        String expResult = "";
        String result = instance.getTraduccionPalabraAutomatica(diccionario, palabra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTraduccionPalabraAutomaticaInversa method, of class Diccionario.
     */
    @Test
    public void testGetTraduccionPalabraAutomaticaInversa() {
        System.out.println("getTraduccionPalabraAutomaticaInversa");
        String palabra = "";
        Diccionario instance = new Diccionario();
        String expResult = "";
        String result = instance.getTraduccionPalabraAutomaticaInversa(palabra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombresDiccionarios method, of class Diccionario.
     */
    @Test
    public void testGetNombresDiccionarios() {
        System.out.println("getNombresDiccionarios");
        Diccionario instance = new Diccionario();
        ArrayList expResult = null;
        ArrayList result = instance.getNombresDiccionarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTablasDiccionario method, of class Diccionario.
     */
    @Test
    public void testGetTablasDiccionario() {
        System.out.println("getTablasDiccionario");
        Diccionario instance = new Diccionario();
        ArrayList expResult = null;
        ArrayList result = instance.getTablasDiccionario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnasBaseDatos method, of class Diccionario.
     */
    @Test
    public void testGetColumnasBaseDatos() {
        System.out.println("getColumnasBaseDatos");
        Diccionario instance = new Diccionario();
        HashMap expResult = null;
        HashMap result = instance.getColumnasBaseDatos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numDiccionarios method, of class Diccionario.
     */
    @Test
    public void testNumDiccionarios() {
        System.out.println("numDiccionarios");
        Diccionario instance = new Diccionario();
        int expResult = 0;
        int result = instance.numDiccionarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numTablasDiccionario method, of class Diccionario.
     */
    @Test
    public void testNumTablasDiccionario() {
        System.out.println("numTablasDiccionario");
        HashMap<String, Object> tablasDiccionario = null;
        Diccionario instance = new Diccionario();
        int expResult = 0;
        int result = instance.numTablasDiccionario(tablasDiccionario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreDiccionario method, of class Diccionario.
     */
    @Test
    public void testGetNombreDiccionario() {
        System.out.println("getNombreDiccionario");
        int posicion = 0;
        Diccionario instance = new Diccionario();
        String expResult = "";
        String result = instance.getNombreDiccionario(posicion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreTablaDiccionario method, of class Diccionario.
     */
    @Test
    public void testGetNombreTablaDiccionario() {
        System.out.println("getNombreTablaDiccionario");
        HashMap<String, Object> tablasDiccionario = null;
        int posicion = 0;
        Diccionario instance = new Diccionario();
        String expResult = "";
        String result = instance.getNombreTablaDiccionario(tablasDiccionario, posicion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of claveAmbigua method, of class Diccionario.
     */
    @Test
    public void testClaveAmbigua() {
        System.out.println("claveAmbigua");
        String clave = "";
        Diccionario instance = new Diccionario();
        boolean expResult = false;
        boolean result = instance.claveAmbigua(clave);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
