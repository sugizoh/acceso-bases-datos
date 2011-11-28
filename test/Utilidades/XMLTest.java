/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
public class XMLTest {

    public XMLTest() {
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

    @Test
    public void testSomeMethod() throws RuntimeException, SAXException, IOException, ParserConfigurationException {
        System.out.println("Creando objeto XML de configuracion.xml");
        XML xml = new XML("configuracion.xml", "basedatos");

        HashMap<String,Object> hashMap = xml.getXmlLeido();
        HashMap<String, HashMap<String, String>> hashMapExp = new HashMap<String, HashMap<String, String>>();

        HashMap<String, Object> hashMapAmazon = new HashMap<String, Object>();
        hashMapAmazon.put("conexion", "jdbc:mysql://localhost/Amazon");
        hashMapAmazon.put("usuario", "root");
        hashMapAmazon.put("password", "root");

        //Rellenar XML

        //Ahora comprobamos que ambos HashMap son iguales
        if(hashMap.size() == hashMapExp.size()) {
            //Iteramos obteniendo las claves
            Iterator it = hashMap.entrySet().iterator();
            Iterator itExp = hashMapExp.entrySet().iterator();

            while (it.hasNext() && itExp.hasNext()) {
                Map.Entry e = (Map.Entry)it.next();
                Map.Entry eExp = (Map.Entry)itExp.next();
                String clave = e.getKey().toString();
                String claveExp = eExp.getKey().toString();

                if(clave.equals(claveExp)) {
                    HashMap<String, String> hashMapValores = (HashMap<String, String>) hashMap.get(clave);
                    HashMap<String, String> hashMapValoresExp = (HashMap<String, String>) hashMapExp.get(clave);

                    if(hashMapValores.size() == hashMapValoresExp.size()) {
                        //Creamos otro iterador para recorrer las claves de los valores
                        Iterator itValores = hashMapValores.entrySet().iterator();
                        Iterator itValoresExp = hashMapValoresExp.entrySet().iterator();

                        while (itValores.hasNext() && itValoresExp.hasNext()) {
                            Map.Entry eValores = (Map.Entry)itValores.next();
                            Map.Entry eValoresExp = (Map.Entry)itValoresExp.next();
                            String claveValores = eValores.getKey().toString();
                            String claveValoresExp = eValoresExp.getKey().toString();

                            if(claveValores.equals(claveValoresExp)) {
                                String valor = hashMapValores.get(claveValores);
                                String valorExp = hashMapValoresExp.get(claveValores);

                                if(!valor.equals(valorExp)) {
                                    fail("No se esperaba para la clave " + claveValores + " de la base de datos " + clave
                                            + " el valor " + valor);
                                }
                            } else {
                                fail("La clave de la base de datos " + clave + " no se esperaba");
                            }
                        }
                    } else {
                        fail("No se han leído los valores correctos para la base de datos " + clave);
                    }
                } else {
                    fail("No se esperaba leer esa clave en el xml");
                }
            }
        } else {
            fail("No se esperaba tener esa cantidad de base de datos");
        }
    }

}