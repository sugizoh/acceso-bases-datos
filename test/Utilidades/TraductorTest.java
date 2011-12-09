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
        String consultaSQL="SELECT substr(titulo,1,10), paginas, precio, nombreAutor FROM Libro, Autor WHERE Libro.idAutor = Autor.idAutor";
        HashMap expResult = new HashMap();
        expResult.put("AMAZON", "SELECT substr(tituloLibro,1,10), numPaginas, precio, nombreAutor FROM Amazon, Autor WHERE Amazon.idAutor = Autor.idAutor");
        expResult.put("CASADELLIBRO", "SELECT substr(titulo,1,10), numPaginas, precio * 1.3755, nombreAutor FROM CasaDelLibro, Autor WHERE CasaDelLibro.idAutor = Autor.idAutor");
        HashMap result = instance.getConsultasTraducidas(consultaSQL);
        if(!expResult.get("AMAZON").equals(result.get("AMAZON")))
        {
            fail("Error al traducir la consulta para amazon");
        }

        if(!expResult.get("CASADELLIBRO").equals(result.get("CASADELLIBRO")))
        {
            fail("Error al traducir la consulta para casa del libro");
        }


        //Realizamos otra traducción para comprobar que también funciona como experaba
        consultaSQL="SELECT *, substr(titulo,1,10), paginas, precio, nombreAutor, lugarNacimiento FROM Libro, Autor WHERE Libro.idAutor = Autor.idAutor";
        expResult = new HashMap();
        expResult.put("AMAZON", "SELECT  nacimiento, Autor.idAutor, apellidosAutor, lugarNacimiento, nombreAutor, "
                + "Amazon.idAutor, idLibro, tituloLibro, \"NULL\", numPaginas, descripcion, editorial, enStock, "
                + "precio, \"NULL\", \"NULL\", fotografia , substr(tituloLibro,1,10), numPaginas, precio, nombreAutor, "
                + "lugarNacimiento FROM Amazon, Autor WHERE Amazon.idAutor = Autor.idAutor");
        expResult.put("CASADELLIBRO", "SELECT  nacimiento, Autor.idAutor, apellidosAutor, lugarNacimiento, "
                + "nombreAutor, CasaDelLibro.idAutor, idLibro, titulo, ISBN, numPaginas, \"NULL\", \"NULL\", \"NULL\", "
                + "precio * 1.3755, sinopsis, fechaEdicion, fotografia , substr(titulo,1,10), numPaginas, precio * 1.3755, "
                + "nombreAutor, lugarNacimiento FROM CasaDelLibro, Autor WHERE CasaDelLibro.idAutor = Autor.idAutor");
        result = instance.getConsultasTraducidas(consultaSQL);
        
        if(!expResult.get("AMAZON").equals(result.get("AMAZON")))
        {
            fail("Error al traducir la consulta para amazon");
        }

        if(!expResult.get("CASADELLIBRO").equals(result.get("CASADELLIBRO")))
        {
            fail("Error al traducir la consulta para casa del libro");
        }
        
    }
}
