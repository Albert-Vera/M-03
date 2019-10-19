package UF_1;

import java.util.*;

public class JugadorsMap {

    public static void main(String[] args) {

        Map<String, String> jugador = new TreeMap<String, String>();

        jugador.put(clauAleatoria(jugador), "Maria");
        jugador.put(clauAleatoria(jugador), "Ramón");
        jugador.put(clauAleatoria(jugador), "Pedro");
        jugador.put(clauAleatoria(jugador), "Pascal");
        jugador.put(clauAleatoria(jugador), "Cesar");
        jugador.put(clauAleatoria(jugador), "Xavier");
        jugador.put(clauAleatoria(jugador), "Bernardo");
        jugador.put(clauAleatoria(jugador), "Jordi");
        jugador.put(clauAleatoria(jugador), "Anna");
        jugador.put(clauAleatoria(jugador), "Isabel");
        jugador.put(clauAleatoria(jugador), "Albert");
        jugador.put(clauAleatoria(jugador), "Alba");
        jugador.put(clauAleatoria(jugador), "Carine");
        jugador.put(clauAleatoria(jugador), "Carme");
        jugador.put(clauAleatoria(jugador), "Josep");
        jugador.put(clauAleatoria(jugador), "Mariano");

                                            // Imprimimos el Map con un Iterador
        Iterator<String > it = jugador.keySet().iterator();

        while(it.hasNext()){
            String clau = it.next();

            if (Integer.parseInt(clau) %2 != 0){                // EN CAS DE SER SENAR
                jugador.put(clau,jugador.get(clau)+ " " + clau);
                System.out.println("Dorsal: " + clau + " -> Nom : " + jugador.get(clau));
            }else
                System.out.println("Dorsal: " + clau + " -> Nom : " + jugador.get(clau));
        }
    }

    static String clauAleatoria(Map<String, String> jugador) {

        String a = "0";
        int b= 0;

        while( jugador.containsKey(a) || a.equals("0")){    // COMPROBAR SI EXISTEIX AQUESTA CLAU

            b = (int)(Math.random()*24+1) ;
            a = String.valueOf(b);
        }
        return a;
    }
}
