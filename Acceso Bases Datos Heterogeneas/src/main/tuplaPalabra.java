package main;

/**
 *
 * @author Jaime Bárez
 * Clase que consiste en un String y un Boolean(true si la palabra es separador)
 */
public class TuplaPalabra {
    
    private String palabra;
    private Boolean esSeparador;
    
    TuplaPalabra(String miPalabra, Boolean esSeparador) {
        setPalabra(miPalabra);
        setEsReservada(esSeparador);
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
    public boolean isEsReservada() {
        return esSeparador;
    }

    /**
     * @param esSeparador el esSeparador a establecer
     */
    private void setEsReservada(boolean esSeparador) {
        this.esSeparador = esSeparador;
    }
} 