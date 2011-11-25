package BD;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    public BD() throws SQLException{
        // Se registra el Driver de MySQL
        DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
    }
    /**
     * Abre la conexión a la base de datos
     * @param cadenaConexion Direccion de la base de datos
     * @param usuario Nombre de usuario de la base de datos
     * @param contraseña Constraseña de la base de datos
     */
    public void abrirBD(String cadenaConexion, String usuario, String contraseña) throws SQLException {
        // Se obtiene una conexión con la base de datos..
        conexion = DriverManager.getConnection (cadenaConexion,usuario, contraseña);

        // Se crea un Statement, para realizar la consulta
        myStatement = conexion.createStatement();
    }
    /**
     * Realiza una consulta y devuelve un ResultSet
     * @param consulta Consulta a realizar
     * @return ResultSet con el resultado de la consulta
     */
    public ResultSet consultar(String consulta) throws SQLException {
        return myStatement.executeQuery (consulta);
    }
    /**
     * Cierra la conexion con la base de datos
     */
    public void cerrar() throws SQLException {
        conexion.close();
    }
}