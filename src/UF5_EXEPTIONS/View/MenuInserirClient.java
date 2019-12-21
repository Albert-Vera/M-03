package UF5_EXEPTIONS.View;

import UF5_EXEPTIONS.DNIincorrectoExeption;
import UF5_EXEPTIONS.Manager.GestorClients;
import UF5_EXEPTIONS.OperacionsBanc;

import java.io.IOException;
import java.util.Scanner;

public class MenuInserirClient {

    void inserirClient() throws IOException, DNIincorrectoExeption {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n-------------------------------------");
            System.out.println("\n\t\t\t\t INSERIR CLIENTS ");
            System.out.println("\n\n\t\t\t 1: Compte nou ");
            System.out.println("\n\n\t\t\t 2: Compte ja existen");
            System.out.println("\n\n\t\t\t 3: Llista de clients");
            System.out.println("\n\n\t\t\t 4: Tornar enrere \n");
            System.out.println("\n-------------------------------------");
            System.out.print("Tria una opció: ");
            int opcions = OperacionsBanc.verifySoloNumerosInteger(sc, " Tria una opció vàlida");

            switch (opcions) {
                case 1:
                    new GestorClients().clientNouCompte();
                    break;
                case 2:
                    new GestorClients().clientCompteExisten();
                    break;
                case 3:
                    new GestorClients().llistaDeClients();
                    break;
                case 4: // sortir
                    return;
                default:
                    System.out.println("No siguis cabezón i tria una opció bona\n");
                    break;
            }
        }
    }
}
