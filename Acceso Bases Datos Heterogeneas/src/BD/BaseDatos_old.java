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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author Jaime Bárez y Miguel González
 */
public class BaseDatos_old
{
    private Connection conexionBD;
    private BaseDatosAmazon bdAmazon;
    private Diccionario_old diccionario;

    /*
     * Constructor de la base de datos
     */
    public BaseDatos_old()
    {
        diccionario = new Diccionario_old();
        
        try
        {
            Class.forName("org.sqlite.JDBC");

            //Abrimos una conexión con la base de datos
            conexionBD = DriverManager.getConnection("jdbc:sqlite:BDLibros.db");

            //Cargamos los datos en la base de datos si no existen
            inicializarBaseDatos();

            //Creamos el objeto para manejar la base de datos de amazon
            bdAmazon = new BaseDatosAmazon(conexionBD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseDatos_old.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException ex) {
            Logger.getLogger(BaseDatos_old.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(BaseDatos_old.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SQLException ex2) {
                Logger.getLogger(BaseDatos_old.class.getName()).log(Level.SEVERE, null, ex2);
            } catch (IOException ioe) {
                Logger.getLogger(BaseDatos_old.class.getName()).log(Level.SEVERE, null, ioe);
            }
        }
    }

    public ArrayList<String> consultaLibros(String sqlConsulta)
    {
        ArrayList<String> resultadosConsulta = new ArrayList<String>();

        //Aquí se tendría que parsear la consulta mediante Regex y finalmente
        String sqlAmazon = diccionario.convertirAmazon(sqlConsulta);

        //resultadosConsulta = bdAmazon.consultaLibros(diccionario.convertirAmazon(sqlConsulta)); //sqlConsulta parseada para la base de datos de amazon

        return resultadosConsulta;
    }
}
class BaseDatosAmazon
{
    private Connection conexionBD;

    /*
     * Constructor de la base de datos de amazon
     */
    public BaseDatosAmazon(Connection conexionBD)
    {
        this.conexionBD = conexionBD;
    }


    public ArrayList<String> consultaLibros(String sqlConsulta)
    {
        ArrayList<String> resultadosConsulta = new ArrayList<String>();
        Statement stat;
        ResultSet rs;
        try {
            //Realizamos la conslta y la recogemos en un ResultSet
            stat = conexionBD.createStatement();
            rs = stat.executeQuery(sqlConsulta);

            //Columnas que se seleccionan, entre SELECT y FROM
            String regexColumnas = "SELECT (.*?) FROM";
            Pattern patternColumnas = Pattern.compile(regexColumnas, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            Matcher matcherColumnas = patternColumnas.matcher(sqlConsulta);
            String columnas = matcherColumnas.group(0);

            //Recorremos las tuplas
            while (rs.next()) {
                String lineaResultado = "";
                if(existeCoincidenciaPatron("idLibro", columnas)) {
                    lineaResultado += "Identidad: " + rs.getInt("idLibro") + "\n";
                }

                if(existeCoincidenciaPatron("tituloLibro", columnas)) {
                    lineaResultado += "Título: " + rs.getString("tituloLibro") + "\n";
                }

                if(existeCoincidenciaPatron("idAutor", columnas)) {
                    lineaResultado += "Identidad del autor: " + rs.getInt("idAutor") + "\n";
                }

                if(existeCoincidenciaPatron("editorial", columnas)) {
                    lineaResultado += "Editorial: " + rs.getString("editorial") + "\n";
                }

                if(existeCoincidenciaPatron("numPaginas", columnas)) {
                    lineaResultado += "Número de páginas: " + rs.getInt("numPaginas") + "\n";
                }

                if(existeCoincidenciaPatron("enStock", columnas)) {
                    lineaResultado += "En stock: " + rs.getBoolean("enStock") + "\n";
                }

                if(existeCoincidenciaPatron("precio", columnas)) {
                    lineaResultado += "Precio: " + rs.getDouble("precio") + "\n";
                }

                if(existeCoincidenciaPatron("descripcion", columnas)) {
                    lineaResultado += "Descripción: " + rs.getString("descripcion") + "\n";
                }

                if(existeCoincidenciaPatron("fotografia", columnas)) {
                    lineaResultado += "Fotografía: " + rs.getString("fotografia") + "\n";
                }

                if(existeCoincidenciaPatron("nombreAutor", columnas)) {
                    lineaResultado += "Nombre del autor: " + rs.getString("nombreAutor") + "\n";
                }

                if(existeCoincidenciaPatron("apellidosAutor", columnas)) {
                    lineaResultado += "Apellidos del autor: " + rs.getString("apellidosAutor") + "\n";
                }

                if(existeCoincidenciaPatron("lugarNacimiento", columnas)) {
                    lineaResultado += "Lugar de nacimiento: " + rs.getString("lugarNacimiento") + "\n";
                }

                if(existeCoincidenciaPatron("nacimiento", columnas)) {
                    lineaResultado += "Año de nacimiento: " + rs.getInt("nacimiento") + "\n";
                }

                resultadosConsulta.add(lineaResultado);
            }

        rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatosAmazon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultadosConsulta;
    }

    private boolean existeCoincidenciaPatron(String expReg, String valor){
        Pattern patron = Pattern.compile(expReg);
        Matcher encajador = patron.matcher(valor);
        return encajador.find();
    }
}