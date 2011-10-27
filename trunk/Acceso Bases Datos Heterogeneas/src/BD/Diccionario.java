package BD;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jaime Bárez y Miguel González
 */
public class Diccionario
{
    ArrayList<String []> palabras;
    ArrayList<String []> bd;

    public Diccionario()
    {
        palabras = new ArrayList<String []>();
        bd = new ArrayList<String []>();

        //Array 0 palabra genérica
        //Array 1 Amazon
        //Array 2 Casa del libro

        String bdLibro[] = new String[5];
        bdLibro[0] = "Libro"; bdLibro[1] = "Amazon"; bdLibro[2] = "CasaDelLibro";
        bdLibro[3] = "Amazon, Autor"; bdLibro[4] = "CasaDelLibro, Autor";
        bd.add(bdLibro);

        String idLibro[] = new String[3];
        idLibro[0] = "idLibro";idLibro[1] = "idLibro";idLibro[2] = "idLibro";
        palabras.add(idLibro);

        String titulo[] = new String[3];
        titulo[0] = "titulo";titulo[1] = "tituloLibro";titulo[2] = "titulo";
        palabras.add(titulo);

        String editorial[] = new String[3];
        editorial[0] = "editorial";editorial[1] = "editorial"; editorial[2] = "'NULL'";
        palabras.add(editorial);

        String ISBN[] = new String[3];
        ISBN[0] = "ISBN"; ISBN[1] = "'NULL'"; ISBN[2] = "ISBN";
        palabras.add(ISBN);

        String paginas[] = new String[3];
        paginas[0] = "paginas"; paginas[1] = "numPaginas"; paginas[2] = "numPaginas";
        palabras.add(paginas);

        String stock[] = new String[3];
        stock[0] = "stock"; stock[1] = "enStock"; stock[2] = "'NULL'";
        palabras.add(stock);

        String precio[] = new String[3];
        precio[0] = "precio"; precio[1] = "precio"; precio[2] = "'NULL'";
        palabras.add(precio);

        String descripcion[] = new String[3];
        descripcion[0] = "descripcion";descripcion[1] = "descripcion";descripcion[2] = "descripcion";
        palabras.add(descripcion);

        String fotografia[] = new String[3];
        fotografia[0] = "fotografia"; fotografia[1] = "fotografia"; fotografia[2] = "fotografia";
        palabras.add(fotografia);

        String fechaEdicion[] = new String[3];
        fechaEdicion[0] = "fechaEdicion"; fechaEdicion[1] = "fechaEdicion"; fechaEdicion[2] = "fechaEdicion";
        palabras.add(fechaEdicion);

        String sinopsis[] = new String[3];
        sinopsis[0] = "sinopsis"; sinopsis[1] = "sinopsis"; sinopsis[2] = "sinopsis";
        palabras.add(sinopsis);

        String idAutor[] = new String[3];
        idAutor[0] = "idAutor"; idAutor[1] = "idAutor"; idAutor[2] = "idAutor";
        palabras.add(idAutor);

        String autor[] = new String[3];
        autor[0] = "autor"; autor[1] = "nombreAutor"; autor[2] = "nombreAutor";
        palabras.add(autor);

        String apellidos[] = new String[3];
        apellidos[0] = "apellidos"; apellidos[1] = "apellidosAutor"; apellidos[2] = "apellidosAutor";
        palabras.add(apellidos);

        String lugarNacimiento[] = new String[3];
        lugarNacimiento[0] = "lugarNacimiento";lugarNacimiento[1] = "lugarNacimiento";lugarNacimiento[2] = "lugarNacimiento";
        palabras.add(lugarNacimiento);

        String fechaNacimiento[] = new String[3];
        fechaNacimiento[0] = "fechaNacimiento";fechaNacimiento[1] = "fechaNacimiento";fechaNacimiento[2] = "fechaNacimiento";
        palabras.add(fechaNacimiento);
    }

    public String convertirAmazon(String sqlConsulta)
    {
        //Parseamos la consulta a mayúsculas
        sqlConsulta = sqlConsulta.toUpperCase();

        //Obtenemos las columnas entre SELECT y FROM
        String regexColumnas = "SELECT (.*?) FROM";
        Pattern patternColumnas = Pattern.compile(regexColumnas, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Matcher matcherColumnas = patternColumnas.matcher(sqlConsulta);

        String stringColumnas = "";
        if(matcherColumnas.find())
        {
            stringColumnas = matcherColumnas.group(1);
        }

        String []columnas = stringColumnas.split(",");
        String sqlPartirFrom = sqlConsulta.substring(sqlConsulta.lastIndexOf("FROM") + "FROM ".length());
        System.out.println("FROM: + " + sqlPartirFrom);
        
        if(sqlConsulta.lastIndexOf("WHERE") == -1 || sqlConsulta.lastIndexOf("GROUP BY") == -1) //Si sólo son las tablas
        {
            
        }
        else
        {
            if(sqlConsulta.lastIndexOf("WHERE") < sqlConsulta.lastIndexOf("GROUP BY")) //Cogemos de FROM hasta WHERE
            {
                
            }
            else //Cogemos de FROM HASTA GROUP BY
            {
                
            }
        }


        //Obtenemos las tablas a partir de FROM
        String regexTablas = "FROM (.*?)";
        Pattern patternTablas = Pattern.compile(regexTablas, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Matcher matcherTablas = patternTablas.matcher(sqlConsulta);

        String tablas = "";

        if(matcherTablas.find())
        {
            //System.out.println("Grupo 1: " + matcherTablas.group(1));
            tablas = matcherTablas.group(1);
        }

       // System.out.println("Convertir amazon tablas: " + tablas);
/*
        for(int i=0; i<palabras.size(); i++)
        {
            //Cambiamos la palabra clave por la palabra de amazon
            Pattern pattern = Pattern.compile(((String [])palabras.get(i))[0]);
            Matcher matcher = pattern.matcher(columnas);

            columnas = matcher.replaceAll(((String [])palabras.get(i))[1]);
        }*/
        /*
        //Obtenemos las tablas seleccionadas
        String regexTablas = "FROM (.*?) ";


        if(agregarRelacionAutores(sql))
        {
            //Cambiamos la palabra clave por la palabra de amazon
            Pattern pattern = Pattern.compile(((String [])bd.get(0))[0]);
            Matcher matcher = pattern.matcher(sql);

            sql = matcher.replaceAll(((String [])bd.get(0))[3]);

            sql += " WHERE Amazon.idAutor = Autor.idAutor";
        }
        else
        {
            //Cambiamos la palabra clave por la palabra de amazon
            Pattern pattern = Pattern.compile(((String [])bd.get(0))[0]);
            Matcher matcher = pattern.matcher(sql);

            sql = matcher.replaceAll(((String [])bd.get(0))[1]);
        }

        for(int i=0; i<palabras.size(); i++)
        {
            //Cambiamos la palabra clave por la palabra de amazon
            Pattern pattern = Pattern.compile(((String [])palabras.get(i))[0]);
            Matcher matcher = pattern.matcher(sql);

            sql = matcher.replaceAll(((String [])palabras.get(i))[1]);
        }

        System.out.println(sql);
        */
        return sqlConsulta;
    }

    public String convertirCasaDelLibro(String sql)
    {
        if(agregarRelacionAutores(sql))
        {
            //Cambiamos la palabra clave por la palabra de amazon
            Pattern pattern = Pattern.compile(((String [])bd.get(0))[0]);
            Matcher matcher = pattern.matcher(sql);

            sql = matcher.replaceAll(((String [])bd.get(0))[4]);

            sql += " WHERE CasaDelLibro.idAutor = Autor.idAutor";
        }
        else
        {
            //Cambiamos la palabra clave por la palabra de amazon
            Pattern pattern = Pattern.compile(((String [])bd.get(0))[0]);
            Matcher matcher = pattern.matcher(sql);

            sql = matcher.replaceAll(((String [])bd.get(0))[2]);
        }

        for(int i=0; i<palabras.size(); i++)
        {
            sql.replaceAll(((String [])palabras.get(i))[0],((String [])palabras.get(i))[2]);
            //Cambiamos la palabra clave por la palabra de amazon
            //Pattern pattern = Pattern.compile(((String [])palabras.get(i))[0]);
            //Matcher matcher = pattern.matcher(sql);

            //matcher.replaceAll(((String [])palabras.get(i))[2]);
        }

        return sql;
    }

    private boolean agregarRelacionAutores(String sql)
    {
        boolean agregarRelacion = false;

        if(sql.contains("autor") || sql.contains("apellidos") || sql.contains("lugarNacimiento")
                || sql.contains("fechaNacimiento"))
        {
            agregarRelacion = true;
        }

        return agregarRelacion;
    }
}
