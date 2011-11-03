package main;
import java.util.ArrayList;

/**
 *
 * @author Jaime Bárez
 */
public class Analizador {
    
    //String de palabras separadoras separadas por el caracter ¬
    private final String palabrasSeparadoras = " ¬,¬.¬;¬=¬>¬<¬(¬)¬\"¬*¬+¬-¬\\¬/";
    //Array de palabras separadoras
    private final String arraySeparadoras[] = palabrasSeparadoras.split("¬");
    
    /**
     * 
     * @param miCadena
     * @return Boolean, que es true si es separadora
     */
    Boolean esSeparadora(String miCadena) {
        //Presuponemos que no es separadora
        Boolean es = false;
        
        //Comparamos nuestra cadena a ver si empieza con alguna de las palabras separadoras
        for (int i=0; i<arraySeparadoras.length && !es; i++) {
            if(miCadena.startsWith(arraySeparadoras[i]))
            {
                es = true;
            }
        }
        return es;
    }
    
    
    
    
    /**
     * Desmiembra una cadena en un ArrayList de dos columnas: 
     * La primera para las distintas palabras y la segunda para un valor Boolean
     * que nos indica si la palabra es separadora o no. (Separadora->true)
     * @param miCadena Cadena a desmembrar
     * @return ArrayList de palabras y Boolean que representa si es separadora o no
     */
    ArrayList desmembrar(String miCadena) {
        
        int longMiCadena = miCadena.length();
        
        ArrayList miArrayList = new ArrayList();
        
        String subCadena= new String();
        
        //Auxiliar que usamos para ir guardando palabras no separadoras
        String noSeparadora = "";
        
        
        for (int i=0; i<longMiCadena; i++) {
            //Vamos analizando la cadena mediante una subcadena que cada vez se hace más pequeña
            subCadena = miCadena.substring(i, longMiCadena);
            
            //Si no hay una palabra separadora al principio
            if(!esSeparadora(subCadena)) {
                //Añadimos letras de la cadena con la que trabajamos a nuestro prototipo de palabra no separadora
                noSeparadora = noSeparadora.concat(Character.toString(subCadena.charAt(0)));
                //Si hemos llegado al final de la cadena inicial, guardamos nuestra palabra no separadora
                if(i==longMiCadena-1){
                    
                    miArrayList.add(new Tupla(noSeparadora, false));
                }
                
            }
            //Si la primera palabra de la cadena que estamos analizando es palabra separadora
            else {
                //Comprobamos que no existiera una no separadora de antes sin guardar.
                //Si existe la guardamos y vaciamos nuestra palabra auxiliar
                if(!noSeparadora.equalsIgnoreCase("")) {
                    
                    miArrayList.add(new Tupla(noSeparadora, false));
                    noSeparadora="";
                }
                //Añadimos al arraylist nuestra palabra separadora
                miArrayList.add(new Tupla(Character.toString(subCadena.charAt(0)), true));
            }
            
        }

        
        return miArrayList;
    }
    
    
    
}