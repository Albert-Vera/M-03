package UF_1;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class mes {
    public static void main(String[] args) {

        Map<Integer, String> jugador = new TreeMap<Integer, String>();

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
        Iterator<Integer > it = jugador.keySet().iterator();

        while(it.hasNext()){
            int clau = it.next();

            if (clau %2 != 0){                // EN CAS DE SER SENAR
                jugador.put(clau,jugador.get(clau)+ " " + clau);
                System.out.println("Dorsal: " + clau + " -> Nom : " + jugador.get(clau));
            }else
                System.out.println("Dorsal: " + clau + " -> Nom : " + jugador.get(clau));
        }
    }

    static Integer clauAleatoria(Map<Integer, String> jugador) {
        int b= 0;

        while( jugador.containsKey(b) || b == 0 ){    // COMPROBAR SI EXISTEIX AQUESTA CLAU

            b = (int)(Math.random()*24+1) ;
        }
        return b;
    }
}
