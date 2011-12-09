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
        
        Configuracion instance = new Configuracion();
        
        if(!instance.getValor("AMAZON", "Usuario").equals("root"))
                fail("Valor no esperado en la configuración");

        if(!instance.getValor("CASADELLIBRO", "Usuario").equals("root"))
                fail("Valor no esperado en la configuración");

        if(!instance.getValor("AMAZON", "Conexion").equals("jdbc:mysql://localhost/Amazon"))
                fail("Valor no esperado en la configuración");

        if(!instance.getValor("CASADELLIBRO", "Conexion").equals("jdbc:mysql://localhost/CasaDelLibro"))
                fail("Valor no esperado en la configuración");
    }

    /**
     * Test of getBaseDatos method, of class Configuracion.
     */
    @Test
    public void testGetBaseDatos() throws Exception {
        Configuracion instance = new Configuracion();
        ArrayList expResult =  new ArrayList();
        expResult.add("CASADELLIBRO");
        expResult.add("AMAZON");

        ArrayList result = instance.getBaseDatos();


        if(expResult.size() == result.size()) {
            for(int i=0; i<expResult.size(); i++) {
                if(!expResult.get(i).equals(result.get(i))) {
                    fail("No se esperaba ese nombre de configuración");
                }
            }
        } else {
            fail("Error en las configuraciones");
        }
    }

    /**
     * Test of numBaseDatos method, of class Configuracion.
     */
    @Test
    public void testNumBaseDatos() throws Exception {
        System.out.println("numBaseDatos");
        Configuracion instance = new Configuracion();
        int expResult = 2;
        int result = instance.numBaseDatos();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombreBaseDatos method, of class Configuracion.
     */
    @Test
    public void testGetNombreBaseDatos() throws Exception {
        System.out.println("getNombreBaseDatos");
        int posicion = 0;
        Configuracion instance = new Configuracion();
        String expResult = "CASADELLIBRO";
        String result = instance.getNombreBaseDatos(posicion);
        assertEquals(expResult, result);

        posicion = 1;
        expResult = "AMAZON";
        result = instance.getNombreBaseDatos(posicion);
        assertEquals(expResult, result);
    }

}