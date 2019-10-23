package UF_1;


import java.util.*;

import static java.util.Collections.*;

public class Llapis implements Comparable<Llapis> {

    private int gruix;
    private int color;

    public Llapis() {
        setColor();
        setGruix();
    }

    public int getGruix() {
        return gruix;
    }

    public void setGruix() {
        this.gruix = (int)(Math.random()*5);
    }

    public int getColor() {
        return color;
    }

    public void setColor() {
        this.color = (int)(Math.random()*5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Llapis)) return false;
        Llapis llapis = (Llapis) o;
        return color == llapis.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    public static void main(String[] args) {
        List<Llapis> llapis = new ArrayList();

        for (int i = 0; i < 10; i++){
            llapis.add(new Llapis());

        }

        ordenarColorAmbCompareTo(llapis);

        ordenarGruixAmbComparable(llapis);

        treureRepetits(llapis);

    }

    @Override
    public int compareTo(Llapis llapis) {

        if (llapis.getColor() < this.color) return 1;
        else
        if ( llapis.getColor() > this.color){
            return -1;
        }
        else return 0;

    }

    static void ordenarColorAmbCompareTo(List<Llapis> llapis){
        sort(llapis);
        System.out.println("    ##########   Llapisos ordenats per Color   amb Comparable######\n");

        for (Llapis llapisList : llapis){
            System.out.println( " Color: " + llapisList.getColor() + "  Gruix:  " + llapisList.getGruix());
        }
    }

    static void ordenarGruixAmbComparable(List<Llapis> llapis){
        sort(llapis, new CompararLlapissos());
        System.out.println("    ##########   Llapisos ordenats per Gruix amb Comparator   ######\n");

        for (Llapis llapisList2 : llapis){
            System.out.println(" Color:  " + llapisList2.getColor() + " Gruix: " + llapisList2.getGruix() );
        }
    }

    static void treureRepetits(List<Llapis> llapissos){
        Set<Llapis> hashSet = new HashSet<>(llapissos);

        System.out.println("\n    ##########   Llapisos sense repetits per Color   ######\n");

        for (Llapis noRepes : hashSet) {
            System.out.println(noRepes.color);
        }
    }


}
