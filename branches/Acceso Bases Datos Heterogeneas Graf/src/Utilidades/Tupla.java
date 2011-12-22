package Utilidades;
/**
 *Clase que consiste en un String y un Boolean(usaremos true si la palabra es separador)
 * @author Miguel González y Jaime Bárez
 * 
 */
public class Tupla {
    
    private String palabra;
    private Boolean esSeparador;
    
    public Tupla(String miPalabra, Boolean esSeparador) {
        setPalabra(miPalabra);
        setEsSeparador(esSeparador);
    }

    /**
     * @return la palabra
     */
    public String getPalabra() {
        return palabra;
    }

    /**
     * @param palabra the palabra a establecer
     */
    private void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    /**
     * @return esSeparador
     */
    public boolean isEsSeparador() {
        return esSeparador;
    }

    /**
     * @param esSeparador el esSeparador a establecer
     */
    private void setEsSeparador(boolean esSeparador) {
        this.esSeparador = esSeparador;
    }
} 