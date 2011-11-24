/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

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
public class TuplaTest {

    public TuplaTest() {
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
     * Test of getPalabra method, of class Tupla.
     */
    @Test
    public void testGetPalabra() {
        System.out.println("Comprobando getPalabra");
        Tupla instance = new Tupla("palabra", false);
        String expResult = "palabra";
        String result = instance.getPalabra();
        assertEquals(expResult, result);

        instance = new Tupla("palabra", true);
        expResult = "palabra";
        result = instance.getPalabra();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEsSeparador method, of class Tupla.
     */
    @Test
    public void testIsEsSeparador() {
        System.out.println("Comprobando isEsSeparador");
        Tupla instance = new Tupla("palabra", false);
        boolean expResult = false;
        boolean result = instance.isEsSeparador();
        assertEquals(expResult, result);

        instance = new Tupla("palabra", true);
        expResult = true;
        result = instance.isEsSeparador();
        assertEquals(expResult, result);
    }

}