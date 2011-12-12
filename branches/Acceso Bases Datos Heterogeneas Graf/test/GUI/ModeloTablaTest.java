/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel González Y Jaime Bárez
 */
public class ModeloTablaTest {

    ModeloTabla instance; //Crearemos una instancia de ModeloTabla para usar en todas las pruebas

    /**
     * Constructor del test ModeloTablaTest
     */
    public ModeloTablaTest() {
        String datos[][] = {{"dato11", "dato12"},{"dato21", "dato22"}};
        String columnas[] = {"Columna1", "Columna2"};
        instance = new ModeloTabla(datos, columnas);
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
     * Test of getColumnCount method, of class ModeloTabla.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("Comprobando el número de columnas");
        
        int expResult = 2;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowCount method, of class ModeloTabla.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("Comprobando el número de filas");
        
        int expResult = 2;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumnName method, of class ModeloTabla.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("Comprobando los nombres de columna");
        int column = 0;
        
        String expResult = "Columna1";
        String result = instance.getColumnName(column);
        assertEquals(expResult, result);

        column = 1;
        expResult = "Columna2";
        result = instance.getColumnName(column);
        assertEquals(expResult, result);
    }

    /**
     * Test of getValueAt method, of class ModeloTabla.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("Comprobando los valores [fila, columna]");
        int row = 0;
        int column = 0;
        
        Object expResult = "dato11";
        Object result = instance.getValueAt(row, column);
        assertEquals(expResult, result);

        row = 0;
        column = 1;

        expResult = "dato12";
        result = instance.getValueAt(row, column);
        assertEquals(expResult, result);

        row = 1;
        column = 0;

        expResult = "dato21";
        result = instance.getValueAt(row, column);
        assertEquals(expResult, result);

        row = 1;
        column = 1;

        expResult = "dato22";
        result = instance.getValueAt(row, column);
        assertEquals(expResult, result);
    }

    /**
     * Test of setValueAt method, of class ModeloTabla.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("Comprobando setValue");
        
        String value = "datoCambiado";
        int row = 1;
        int column = 0;
        
        instance.setValueAt(value, row, column);

        String datos[][] = {{"dato11", "dato12"},{"datoCambiado", "dato22"}};
        String columnas[] = {"Columna1", "Columna2"};
        ModeloTabla instanceExp = new ModeloTabla(datos, columnas);

        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                if(!instance.getValueAt(i, j).equals(instanceExp.getValueAt(i, j))) {
                    fail("No se ha modificado el valor en su lugar correcto");
                }
            }
        }
    }

}