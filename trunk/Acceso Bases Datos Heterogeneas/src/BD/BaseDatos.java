package BD;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaime Bárez y Miguel González
 */
public class BaseDatos
{
    private Connection conexionBD;


    /*
     * Constructor de la base de datos
     */
    public BaseDatos()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");

            //Abrimos una conexión con la base de datos
            conexionBD = DriverManager.getConnection("jdbc:sqlite:BDLibros.db");

            //Cargamos los datos en la base de datos si no existen
            inicializarBaseDatos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inicializarBaseDatos()
    {
        Statement stat;
        ResultSet rs;

        try {
            stat = conexionBD.createStatement();
            rs = stat.executeQuery("select * from Amazon;");
            rs.close();
            //No hacemos nada, la tabla contiene todos los datos
        } catch (SQLException ex) {
            //Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);

            try {
                //No existe la tabla Amazon, por lo tanto, la base de datos no contiene tablas ni datos
                //Cargamos el fichero sql para crear las tablas e inicializar los datos y ejecutamos la consulta
                String sqlUpdate = "";
                
                FileReader fr = new FileReader("BDLibrosUpdate.sql");
                BufferedReader bf = new BufferedReader(fr);

                String cadenaActual = "";
                while ((cadenaActual = bf.readLine()) != null) {
                    sqlUpdate += cadenaActual;
                }

                String sqlUpdateArray[] = sqlUpdate.split(";");

                stat = conexionBD.createStatement();

                for(int i=0; i<sqlUpdateArray.length; i++)
                    stat.executeUpdate(sqlUpdateArray[i]);

                fr = new FileReader("BDLibrosInsert.sql");
                bf = new BufferedReader(fr);

                String sqlInsert = "";
                while ((cadenaActual = bf.readLine()) != null) {
                    sqlInsert += cadenaActual;
                }

                String sqlInsertArray[] = sqlInsert.split(";");
                for(int i=0; i<sqlInsertArray.length; i++)
                    stat.executeUpdate(sqlInsertArray[i]);
                //Tablas creadas correctamente
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SQLException ex2) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex2);
            } catch (IOException ioe) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ioe);
            }
        }
    }
}
