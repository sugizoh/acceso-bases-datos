/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import XML.Diccionario;
import java.io.IOException;
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
public class TraductorTest {
    
    Diccionario diccionario;
    
    public TraductorTest() throws RuntimeException, SAXException, IOException, ParserConfigurationException {
        diccionario = new Diccionario();
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
     * Test of getConsultasTraducidas method, of class Traductor.
     */
    @Test
    public void testGetConsultasTraducidas() throws Exception {
        System.out.println("getConsultasTraducidas");
        Traductor instance = new Traductor();
        String consultaSQL="SELECT substr(titulo,1,10), paginas, precio, nombreAutor FROM Libro WHERE Libro.idAutor = Autor.idAutor";
        HashMap expResult = new HashMap();
        expResult.put("AMAZON", "SELECT substr(tituloLibro,1,10), numPaginas, precio, nombreAutor FROM Amazon WHERE Amazon.idAutor = Autor.idAutor");
        expResult.put("CASADELLIBRO", "SELECT substr(titulo,1,10), numPaginas, precio, nombreAutor FROM CasaDelLibro WHERE CasaDelLibro.idAutor = Autor.idAutor");
        HashMap result = instance.getConsultasTraducidas(consultaSQL);
        if(!expResult.get("AMAZON").equals(result.get("AMAZON")))
        {
            fail("Contenido distinto");
        }
           // assertEquals(expResult.get("CASADELLIBRO"), result.get("AMAZON"));
        
    }
}
