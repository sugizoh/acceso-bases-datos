/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import XML.Configuracion;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author JaimeInves
 */
public class Consultor {
    public ArrayList<String> lanzarConsulta(HashMap<String, String> sentenciasSQL) {
        ArrayList<String> tuplas = new ArrayList<String>();
        Configuracion config = new Configuracion();
        BD baseDatos = new BD();
        for(int i=0; i<config.numBaseDatos(); i++) {
            baseDatos.abrirBD(null, null, null);
        }
        
        //sentenciasSQL.get(this)
        return tuplas;
    }
}
