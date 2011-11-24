/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.*;

/**
 * Clase que maneja las operaciones de bases de datos
 * @author JaimeInves
 */
public class BD {
    private Connection conexion;
    private Statement myStatement;
    /**
     * Constructor
     */
    public BD() throws SQLException{
        // Se registra el Driver de MySQL
        DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
    }
    
    public void abrirBD(String cadenaConexion, String usuario, String contraseña) throws SQLException {
        // Se obtiene una conexión con la base de datos..
        conexion = DriverManager.getConnection (cadenaConexion,usuario, contraseña);

        // Se crea un Statement, para realizar la consulta
        myStatement = conexion.createStatement();
    }
    
    public ResultSet consultar(String consulta) throws SQLException {
        return myStatement.executeQuery (consulta);
    }
    
    public void cerrar() throws SQLException {
        conexion.close();
    }
}