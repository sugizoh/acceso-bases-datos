package main;

/**
 *
 * @author JaimeInves
 */
   
public class tuplaPalabra {
    
    private String palabra;
    private boolean esReservada;
    
    tuplaPalabra(String miPalabra, Boolean esReservada) {
        setPalabra(miPalabra);
        setEsReservada(esReservada);
    }

    /**
     * @return the palabra
     */
    public String getPalabra() {
        return palabra;
    }

    /**
     * @param palabra the palabra to set
     */
    private void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    /**
     * @return the esReservada
     */
    public boolean isEsReservada() {
        return esReservada;
    }

    /**
     * @param esReservada the esReservada to set
     */
    private void setEsReservada(boolean esReservada) {
        this.esReservada = esReservada;
    }
} 