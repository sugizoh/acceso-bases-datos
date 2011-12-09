/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
public class DiccionarioTest {
    
    public static Diccionario instance;
    
    public DiccionarioTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        instance = new Diccionario();
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
        String baseDatos = "Amazon";
        String tabla = "Libro";
        String palabra = "stock";
        
        String expResult = "enStock";
        String result = instance.getTraduccionPalabra(baseDatos, tabla, palabra);
        assertEquals(expResult, result);
        
        baseDatos = "CasaDelLibro";
        expResult = "\"NULL\"";
        
        result = instance.getTraduccionPalabra(baseDatos, tabla, palabra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTraduccionPalabraAutomatica method, of class Diccionario.
     */
    @Test
    public void testGetTraduccionPalabraAutomatica() {
        System.out.println("getTraduccionPalabraAutomatica");
        String diccionario = "Amazon";
        String palabra = "stock";
        
        String expResult = "enStock";
        String result = instance.getTraduccionPalabraAutomatica(diccionario, palabra);
        assertEquals(expResult, result);
        
        diccionario = "CasaDelLibro";
        expResult = "\"NULL\"";
        result = instance.getTraduccionPalabraAutomatica(diccionario, palabra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTraduccionPalabraAutomaticaInversa method, of class Diccionario.
     */
    @Test
    public void testGetTraduccionPalabraAutomaticaInversa() {
        System.out.println("getTraduccionPalabraAutomaticaInversa");
        String palabra = "apellidosAutor";
        String expResult = "APELLIDOS";
        String result = instance.getTraduccionPalabraAutomaticaInversa(palabra);
        assertEquals(expResult, result);
        
        
        palabra = "numPaginas";
        expResult = "PAGINAS";
        result = instance.getTraduccionPalabraAutomaticaInversa(palabra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNombresDiccionarios method, of class Diccionario.
     */
    @Test
    public void testGetNombresDiccionarios() {
        System.out.println("getNombresDiccionarios");
        
        ArrayList expResult = new ArrayList();
        expResult.add("CASADELLIBRO");
        expResult.add("AMAZON");
        ArrayList result = instance.getNombresDiccionarios();
        if (expResult.size() != result.size())
        {
            fail("Distintos");
        }
        else
        {
            for(int i=0; i<expResult.size(); i++)
            {
                assertEquals(expResult.get(i), result.get(i));
            }
        }
    }

    /**
     * Test of getTablasDiccionario method, of class Diccionario.
     */
    @Test
    public void testGetTablasDiccionario() {
        System.out.println("getTablasDiccionario");
        
        ArrayList expResult = new ArrayList();
        expResult.add("AUTOR");
        expResult.add("LIBRO");
        ArrayList result = instance.getTablasDiccionario();
        if (expResult.size() != result.size())
        {
            fail("Distintos");
        }
        else
        {
            for(int i=0; i<expResult.size(); i++)
            {
                assertEquals(expResult.get(i), result.get(i));
            }
        }
    }

    /**
     * Test of getColumnasBaseDatos method, of class Diccionario.
     */
    @Test
    public void testGetColumnasBaseDatos() {
        System.out.println("getColumnasBaseDatos");
        
        String[] Libro = {"IDAUTOR", "IDLIBRO", "TITULO", "ISBN", "PAGINAS",
        "DESCRIPCION", "EDITORIAL", "STOCK", "PRECIO", "SINOPSIS", "FECHAEDICION", "FOTOGRAFIA"};
        
        String[] Autor = {"FECHANACIMIENTO", "IDAUTOR", "APELLIDOS", "LUGARNACIMIENTO", "NOMBREAUTOR"};
        
        ArrayList columnasLibro = new ArrayList();
        ArrayList columnasAutor = new ArrayList();
        
        for(int i=0; i<Libro.length; i++)
        {
            columnasLibro.add(Libro[i]);
        }
        
        for(int i=0; i<Autor.length; i++)
        {
            columnasAutor.add(Autor[i]);
        }

        
        HashMap expResult = new HashMap();
        expResult.put("AUTOR", columnasAutor);
        expResult.put("Libro", columnasLibro);
        
        HashMap result = instance.getColumnasBaseDatos();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of numDiccionarios method, of class Diccionario.
     */
    @Test
    public void testNumDiccionarios() {
        System.out.println("numDiccionarios");
        
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
        
        boolean expResult = false;
        boolean result = instance.claveAmbigua(clave);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}