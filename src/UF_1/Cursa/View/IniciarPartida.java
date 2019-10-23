package UF_1.Cursa.View;

import UF_1.Cursa.Manager.Jugar;
import UF_1.Cursa.Manager.Configuracio;
import UF_1.Cursa.Manager.Resultats;

import java.util.Scanner;

public class IniciarPartida {
    static void menu(){

        Scanner entrada = new Scanner(System.in);

        while (true) {
            System.out.println("Cursa 2019");
            System.out.println("Opciones: ");
            System.out.println(" 1. Configuració de la competició");
            System.out.println(" 2. Resultats/Palmarès");
            System.out.println(" 3. Jugar");
            System.out.println(" 4. Acabar");
            System.out.println();
            System.out.print("Elige una opción: ");
            int opcion = entrada.nextInt();

            switch (opcion) {

                case 1:
                    new Configuracio().configurar();
                    break;
                case 2: // pantalla
                    new Resultats().palmares();
                    break;

                case 3: //
                    new Jugar().iniciarJoc();
                    break;

                case 4: //
                    System.exit(0);


                default:
                    System.exit(0);
            }
        }
    }
}
