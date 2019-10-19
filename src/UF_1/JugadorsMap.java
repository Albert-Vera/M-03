package UF_1;

import java.util.*;

public class JugadorsMap {

    private int clau;


//    public int getClau() {
//        return clau;
//    }
//
//    public void setClau(int clau) {
//        this.clau = (int)(Math.random()*24 + 1);
//    }
//
//    public JugadorsMap() {
//        setClau();
//    }

    public static void main(String[] args) {

        Map<Integer, String> jugador = new TreeMap<Integer, String>();
        //List<JugadorsMap> keys = new ArrayList<>();
        //map.get(keysAsArray.get(r.nextInt(keysAsArray.size()))
        jugador.put((int)(Math.random()*24), "Maria");
        jugador.put((int)(Math.random()*24), "Ramón");
        jugador.put(3, "Pedro");
        jugador.put(5, "Pascal");
        jugador.put(11, "Cesar");
        jugador.put(14, "Xavier");
        jugador.put(16, "Bernardo");
        jugador.put(8, "Jordi");
        jugador.put(18, "Anna");
        jugador.put(6, "Isabel");
        jugador.put(7, "Albert");
        jugador.put(16, "Alba");
        jugador.put(8, "Carine");
        jugador.put(18, "Carme");
        jugador.put(6, "Josep");
        jugador.put(7, "Mariano");

// Imprimimos el Map con un Iterador
        Iterator<Integer> it = jugador.keySet().iterator();
        
        while(it.hasNext()){
            Integer clau = it.next();
            System.out.println("Clave: " + clau + " -> Valor: " + jugador.get(clau));
        }
    }
    static Integer randomKey(){

        ArrayList<Integer> dorsal = new ArrayList();
        Random R = new Random();

       return dorsal.get(R.nextInt(24));
    }
}
