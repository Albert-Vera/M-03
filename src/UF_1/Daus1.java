package UF_1;

import java.util.Random;
import java.util.Scanner;

public class Daus1 {
    public static void main(String[] args) {

        int [] daus = new int[13];
        int dado1, dado2, sumaDados, opcion= 1;
        Random r = new Random();
        Scanner entrada = new Scanner(System.in);

        while (opcion == 1){
            System.out.println("          ##############      JUEGO DE DADOS    ##################");
            System.out.println();
            System.out.println("  (1)  Jugar");
            System.out.println("  (0)  Salir del juego");
            opcion = entrada.nextInt();

            if (opcion == 1) {

                dado1 = r.nextInt(6) + 1;
                dado2 = r.nextInt(6) + 1;
                sumaDados = dado1 + dado2;
                System.out.println(sumaDados);

                daus[sumaDados]++;

            }else {

                if(opcion != 0){
                    System.out.println(" Introduce solo valores de 0 o 1 ");
                }
            }
        }
        for (int i = 2; i < 13; i++) {
            System.out.println("El " + i + " ha salido: " + daus[i] + " veces");
        }

    }
}
