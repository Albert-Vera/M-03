package UF_1.Juegos.Dados;

import UF_1.Juegos.Dados.MenuDados;

import java.io.IOException;
import java.util.Scanner;

public class Menu_Juegos {
    public static void main(String[] args) throws IOException {

        Scanner entrada = new Scanner(System.in);


        while (true) {

            System.out.println();
            System.out.println("   ####################################");
            System.out.println("   ######   LISTA DE JUEGOS    ########");
            System.out.println("   ####################################");
            System.out.println();
            System.out.println(" 1. Dados");
            System.out.println(" 2. Damas");
            System.out.println(" 3. Gran Turismo");
            System.out.println(" 4. Modern Warcraft");
            System.out.println(" 0. Salir del Juego");
            System.out.println();
            System.out.print("Elige una opción: ");
            int opcion = entrada.nextInt();

            switch (opcion) {

                case 1:
                    new MenuDados();
                    break;
                case 2:
                    System.out.println(" Juego en Construcción");
                    break;

                case 3:
                    System.out.println(" Juego en Construcción");
                    break;

                case 4:
                    System.out.println(" Juego en Construcción");
                    break;

                case 0:
                    System.exit(0);
                default:
                    System.out.println("INTRODUCE UNA OPCIÓN CORRECTA");
            }
        }
    }
}
