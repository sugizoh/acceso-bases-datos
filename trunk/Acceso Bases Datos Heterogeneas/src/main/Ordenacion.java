package main;

public class Ordenacion
{
    public static String [] ordenarString(String []palabras) {
        return Ordenamiento_por_mezcla.OrdenaMerge(palabras);
    }
}

//Algoritmo de ordenación por mezcla
//Sacado
//http://es.wikipedia.org/wiki/Ordenamiento_por_mezcla
class Ordenamiento_por_mezcla{
     public static String[] OrdenaMerge(String[] L) {
        int n = L.length;

        if (n > 1){
            int m = (int) (Math.ceil(n/2.0));
            String [] L1 = new String[m];
            String [] L2 = new String[n-m];

            for (int i = 0; i < m; i++){
                L1[i] = L[i];
            }
            for (int i = m; i < n; i++){
                L2[i-m] = L[i];
            }
            L = merge(OrdenaMerge(L1), OrdenaMerge(L2));
        }
        return L;
    }

    public static String[] eliminar(String [] l){
        String [] L = new String[l.length-1];
        for(int i = 1; i < l.length; i++){
            L[i-1] = l[i];
        }
        return L;
    }

    public static String[] merge(String[] L1, String[] L2) {
         String[] L = new String[L1.length+L2.length];
         int i = 0;
         while ((L1.length != 0) && (L2.length != 0)) {
             if (L1[0].length() > L2[0].length()){
                 L[i++] = L1[0];
                 L1 = eliminar(L1);
                 if (L1.length == 0){
                     while (L2.length != 0) {
                         L[i++] = L2[0];
                         L2 = eliminar(L2);
                     }
                 }
             }
             else{
                 L[i++] = L2[0];
                 L2 = eliminar(L2);
                 if (L2.length == 0) {
                    while (L1.length != 0) {
                         L[i++] = L1[0];
                         L1 = eliminar(L1);
                    }
                 }
             }
         }
         return L;
    }
}
