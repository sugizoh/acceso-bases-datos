package main;
import java.util.ArrayList;

/**
 *
 * @author Jaime Bárez
 */
public class Diccionario {
    //Lista de palabras reservadas separadas por el símbolo ¬
    //No usamos la coma para separar, ya que la consideramos reservada
    //-------------------------
    //-----------REVISAR----------------
    //---------------------
    private final String palabrasReservadas = " ¬,¬=¬>¬<¬(¬)¬\"¬*¬+¬-¬\\¬/¬SELECT¬FROM¬WHERE¬CHAT¬CONCAT¬FIELD¬FORMAT¬HEX¬INSERT¬INSTR¬LCASE¬LEFT¬LIKE¬LOCATE¬LOWER¬LPAD¬LTRIM¬MATCH¬MID¬NOT¬ORD¬POSITION¬QUOTE¬REPLACE¬REVERSE¬RIGHT¬RLIKE¬RPAD¬RTRIM¬SPACE¬STRCMP¬SUBSTR¬TRIM¬UCASE¬UPPER¬SUBSTRING¬COUNT¬ADD¬ALL¬ALTER¬ANALYZE¬AND¬AS¬ASC¬ASENSITIVE¬BEFORE¬BETWEEN¬BIGINT¬BINARY¬BLOB¬BOTH¬BY¬CALL¬CASCADE¬CASE¬CHANGE¬CHAR¬CHARACTER¬CHECK¬COLLATE¬COLUMN¬CONDITION¬CONSTRAINT¬CONTINUE¬CONVERT¬COUNT¬CREATE¬CROSS¬CURRENT_DATE¬CURRENT_TIME¬CURRENT_TIMESTAMP¬CURRENT_USER¬CURSOR¬DATABASE¬DATABASES¬DAY_HOUR¬DAY_MICROSECOND¬DAY_MINUTE¬DAY_SECOND¬DEC¬DECIMAL¬DECLARE¬DEFAULT¬DELAYED¬DELETE¬DESC¬DESCRIBE¬DETERMINISTIC¬DISTINCT¬DISTINCTROW¬DIV¬DOUBLE¬DROP¬DUAL¬EACH¬ELSE¬ELSEIF¬ENCLOSED¬ESCAPED¬EXISTS¬EXIT¬EXPLAIN¬FALSE¬FETCH¬FLOAT¬FLOAT4¬FLOAT8¬FOR¬FORCE¬FOREIGN¬FROM¬FULLTEXT¬GRANT¬GROUP¬HAVING¬HIGH_PRIORITY¬HOUR_MICROSECOND¬HOUR_MINUTE¬HOUR_SECOND¬IF¬IGNORE¬IN¬INDEX¬INFILE¬INNER¬INOUT¬INSENSITIVE¬INSERT¬INT¬INT1¬INT2¬INT3¬INT4¬INT8¬INTEGER¬INTERVAL¬INTO¬IS¬ITERATE¬JOIN¬KEY¬KEYS¬KILL¬LEADING¬LEAVE¬LEFT¬LIKE¬LIMIT¬LINES¬LOAD¬LOCALTIME¬LOCALTIMESTAMP¬LOCK¬LONG¬LONGBLOB¬LONGTEXT¬LOOP¬LOW_PRIORITY¬MATCH¬MEDIUMBLOB¬MEDIUMINT¬MEDIUMTEXT¬MIDDLEINT¬MINUTE_MICROSECOND¬MINUTE_SECOND¬MOD¬MODIFIES¬NATURAL¬NOT¬NO_WRITE_TO_BINLOG¬NULL¬NUMERIC¬ON¬OPTIMIZE¬OPTION¬OPTIONALLY¬OR¬ORDER¬OUT¬OUTER¬OUTFILE¬PRECISION¬PRIMARY¬PROCEDURE¬PURGE¬READ¬READS¬REAL¬REFERENCES¬REGEXP¬RELEASE¬RENAME¬REPEAT¬REPLACE¬REQUIRE¬RESTRICT¬RETURN¬REVOKE¬RIGHT¬RLIKE¬SCHEMA¬SCHEMAS¬SECOND_MICROSECOND¬SELECT¬SENSITIVE¬SEPARATOR¬SET¬SHOW¬SMALLINT¬SONAME¬SPATIAL¬SPECIFIC¬SQL¬SQLEXCEPTION¬SQLSTATE¬SQLWARNING¬SQL_BIG_RESULT¬SQL_CALC_FOUND_ROWS¬SQL_SMALL_RESULT¬SSL¬STARTING¬STRAIGHT_JOIN¬TABLE¬TERMINATED¬THEN¬TINYBLOB¬TINYINT¬TINYTEXT¬ TO ¬TRAILING¬TRIGGER¬TRUE¬UNDO¬UNION¬UNIQUE¬UNLOCK¬UNSIGNED¬UPDATE¬USAGE¬USE¬USING¬UTC_DATE¬UTC_TIME¬UTC_TIMESTAMP¬VALUES¬VARBINARY¬VARCHAR¬VARCHARACTER¬VARYING¬WHEN¬WHERE¬WHILE¬WITH¬WRITE¬XOR¬YEAR_MONTH¬ZEROFILL";
    /*Se ordena el array de mayor a menor para evitar hacer interpretar como 
    no reservada por. ej. SUBSTR cuando el usuario ha escrito SUBSTRING 
    que es otra función equivalente de mysql, ya que el array al comparar
    se recorre de izquierda a derecha*/
    private final String arrayReservadas[] = Ordenacion.ordenarString(palabrasReservadas.split("¬"));
    /**
     * Nos muestra si una cadena comienza por palabra reservada, devolviendo el número de letras de la misma. Si no lo es, devuelve -1
     * @param miCadena 
     * @return int Número de letras de la palabra reservada por la que comienza, si no empieza por reservada, devuelve -1
     */
    int esReservada(String miCadena) {
        //Trabajaremos con una cadena local, que es la recibida pasada a mayúsculas(ya que no importa la capitalización de las letras)
        String cadenaLocal = miCadena.toUpperCase();
        //Presuponemos que no es reservada
        int es = -1;
        //Lista de palabras reservadas:
        //String palabrasReservadas = "SELECT, FROM, WHERE, GROUP BY, HAVING, ORDER BY";
        //Array de palabras reservadas
        //String arrayReservadas[] = palabrasReservadas.split(", ");
        
        //Comparamos nuestra cadena a ver si empieza con alguna de las palabras reservadas
        for (int i=0; i<arrayReservadas.length && es==-1; i++) {
            if(cadenaLocal.startsWith(arrayReservadas[i]))
            {
                es = arrayReservadas[i].length();
            }
        }
        return es;
    }
    
    /**
     * Desmiembra una cadena en un ArrayList de dos columnas: 
     * La primera para las distintas palabras y la segunda para un valor Boolean
     * que nos indica si la palabra es reservada o no. (Reservada->true)
     * @param miCadena Cadena a desmembrar
     * @return ArrayList de palabras y Boolean que representa si es reservada o no
     */
    ArrayList desmembrar(String miCadena) {
        
        int longMiCadena = miCadena.length();
        
        ArrayList miArrayList = new ArrayList();
        
        String subCadena= new String();
        int longPalabraReservada=0;
        
        //Auxiliar que usamos para ir guardando palabras no reservadas
        String noReservada = "";
        
        
        for (int i=0; i<longMiCadena; i++) {
            //Vamos analizando la cadena mediante una subcadena que cada vez se hace más pequeña
            subCadena = miCadena.substring(i, longMiCadena);
            //Longitud de la palabra reservada al principio de la cadena. Si no la hay, es -1
            longPalabraReservada = esReservada(subCadena);
            
            //Si no hay una palabra reservada al principio
            if(longPalabraReservada<=0) {
                //Añadimos la primera letra de la cadena con la que trabajamos a nuestro prototipo de palabra no reservada
                noReservada = noReservada.concat(Character.toString(subCadena.charAt(0)));
                //Si hemos llegado al final de la cadena inicial, guardamos nuestra palabra no reservada
                if(i==longMiCadena-1){
                    miArrayList.add(new tuplaPalabra(noReservada, false));
                }
                
            }
            //Si la primera palabra de la cadena que estamos analizando es palabra reservada
            else {
                //Comprobamos que no existiera una no reservada de antes sin guardar. Si existe la guardamos y vaciamos nuestra palabra auxiliar
                if(!noReservada.equalsIgnoreCase("")) {
                    miArrayList.add(new tuplaPalabra(noReservada, false));
                    noReservada="";
                }
                //Añadimos al arraylist nuestra palabra reservada
                miArrayList.add(new tuplaPalabra(miCadena.substring(i, i + longPalabraReservada), true));
                i = i + longPalabraReservada-1;
            }
            
        }

        
        return miArrayList;
    }
    
    
    
}