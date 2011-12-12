package Utilidades;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *Clase que se encarga de analizar cadenas
 * @author Miguel González y Jaime Bárez
 */
public class Analizador {

    private final String palabrasSeparadoras;
    private final String palabrasSeparadorasRegex;
    /**
     * Constructor
     */
    public Analizador()
    {
        palabrasSeparadoras = " ,.;=><()\\*+-\\/";
        //Creamos la expresión regular. O no contiene la palabra separadoras Ó contiene separadores Ó contiene nombres de columna entre comillas
        palabrasSeparadorasRegex = "[^" + palabrasSeparadoras + "]+|[" + palabrasSeparadoras + "]+|`[.]*`|'[.]*'|\"[.]*\"";
    }

    /**
     * Desmiembra una cadena en un ArrayList de dos columnas: 
     * La primera para las distintas palabras y la segunda para un valor Boolean
     * que nos indica si la palabra es separadora o no. (Separadora->true)
     * @param miCadena Cadena a desmembrar
     * @return ArrayList de palabras y Boolean que representa si es separadora o no
     */
    public ArrayList<Tupla> desmembrar(String miCadena) {

        ArrayList<Tupla> miArrayList = new ArrayList<Tupla>();

        Pattern patron = Pattern.compile(palabrasSeparadorasRegex);
        Matcher encaja = patron.matcher(miCadena);

        while(encaja.find())
        {
            String palabraEncajada = miCadena.substring(encaja.start(), encaja.end());

            //Si el primer digito está en palabrasSeparadoras es un separador la cadena
            if(palabrasSeparadoras.contains(String.valueOf(palabraEncajada.charAt(0))))
                miArrayList.add(new Tupla(palabraEncajada, true));
            else
                miArrayList.add(new Tupla(palabraEncajada, false));
        }

        return miArrayList;
    }
}