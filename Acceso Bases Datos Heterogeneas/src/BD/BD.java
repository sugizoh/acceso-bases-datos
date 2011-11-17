package BD;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que maneja las operaciones de bases de datos
 * @author Miguel González y Jaime Bárez
 */
public class BD {
    private Connection conexion;
    private Statement myStatement;
    /**
     * Constructor
     */
    public BD() {
        try {
            // Se registra el Driver de MySQL
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
        } catch (SQLException ex) {
            //Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void abrirBD(String cadenaConexion, String usuario, String contraseña) {
        try {
            // Se obtiene una conexión con la base de datos..
            conexion = DriverManager.getConnection (
            cadenaConexion,usuario, contraseña);

            // Se crea un Statement, para realizar la consulta
            myStatement = conexion.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public ResultSet consultar(String consulta) {
        ResultSet rs = null;
        try {
            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = myStatement.executeQuery (consulta);
        } catch (SQLException ex) {
            //Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void cerrar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            //Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}