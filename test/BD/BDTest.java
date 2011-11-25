/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.DriverManager;
import com.mysql.jdbc.Statement;
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
public class BDTest {
    
    public BDTest() {
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
     * Test of abrirBD method, of class BD.
     */
    @Test
    public void testAbrirBD() throws Exception {
        System.out.println("abrirBD");
        String cadenaConexion = "jdbc:mysql://localhost/amazon";
        String usuario = "root";
        String contraseña = "sql";
        BD instance = new BD();
        instance.abrirBD(cadenaConexion, usuario, contraseña);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of consultar method, of class BD.
     */
    @Test
    public void testConsultar() throws Exception {
        System.out.println("consultar");
        String consulta = "SELECT substr(titulo,1,10), fechaEdicion, precio, nombreAutor FROM Libro, Autor WHERE Libro.idAutor = Autor.idAutor";
        BD instance = new BD();
        ResultSet expResult = null;
        ResultSet result = instance.consultar(consulta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    public ResultSet resultadoConsulta(String consulta) throws SQLException {
        Statement myStatement = null;
        return myStatement.executeQuery (consulta);
    }

    /**
     * Test of cerrar method, of class BD.
     */
    @Test
    public void testCerrar() throws Exception {
        System.out.println("cerrar");
        BD instance = new BD();
        instance.abrirBD("jdbc:mysql://localhost/amazon", "root", "sql");
        instance.cerrar();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
