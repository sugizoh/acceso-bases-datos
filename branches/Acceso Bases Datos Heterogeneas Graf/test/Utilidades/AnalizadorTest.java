/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

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
public class AnalizadorTest {

    public AnalizadorTest() {
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
     * Test of desmembrar method, of class Analizador.
     */
    @Test
    public void testDesmembrar() {
        System.out.println("desmembrar");
        String miCadena = "SELECT columna1, 'columnaString', 2*3 FROM BaseDatos;";
        Analizador instance = new Analizador();

        //Rellenamos el ArrayList esperado
        ArrayList<Tupla> expResult = new ArrayList<Tupla>();
        expResult.add(new Tupla("SELECT", false));
        expResult.add(new Tupla(" ", true));
        expResult.add(new Tupla("columna1", false));
        expResult.add(new Tupla(", ", true));
        expResult.add(new Tupla("'columnaString'", false));
        expResult.add(new Tupla(", ", true));
        expResult.add(new Tupla("2", false));
        expResult.add(new Tupla("*", true));
        expResult.add(new Tupla("3", false));
        expResult.add(new Tupla(" ", true));
        expResult.add(new Tupla("FROM", false));
        expResult.add(new Tupla(" ", true));
        expResult.add(new Tupla("BaseDatos", false));
        expResult.add(new Tupla(";", true));

        ArrayList<Tupla> result = instance.desmembrar(miCadena);

        if(result.size() == expResult.size()) {
            for(int i=0; i<result.size(); i++) {
                if(!(result.get(i).getPalabra().equals(expResult.get(i).getPalabra())
                        && result.get(i).isEsSeparador() == expResult.get(i).isEsSeparador())) {
                    fail("Error al desmembrar las tuplas, se esperaba la palabra: " + expResult.get(i).getPalabra() + "|"
                            + expResult.get(i).isEsSeparador() + ", y se encontró con: " + result.get(i).getPalabra() + "|"
                            + result.get(i).isEsSeparador());
                }
            }
        } else {
            fail("Error en la longitud esperada del ArrayList<Tupla> devuelto por desmembrar");
        }
    }

}