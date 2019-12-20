package UF5_EXEPTIONS.View;

import UF5_EXEPTIONS.Account_ZeroUser;
import UF5_EXEPTIONS.Model.CompteEstalvi;
import UF5_EXEPTIONS.DNIincorrectoExeption;
import UF5_EXEPTIONS.Manager.GestorClients;
import UF5_EXEPTIONS.OperacionsBanc;

import java.io.IOException;
import java.util.Scanner;

public class MenuGestorClients {

    CompteEstalvi compteEstalvi = new CompteEstalvi();
    public void menuGestor () throws IOException, DNIincorrectoExeption, Account_ZeroUser {
        boolean existeixClient;
        Scanner sc = new Scanner(System.in);
        GestorClients gestorClients = new GestorClients();

        while (true) {
            System.out.println("\n-------------------------------------");
            System.out.println("\n\t\t\t\t MENU GESTOR ");
            System.out.println("\n\n\t\t\t 1: Inserir Client ");
            System.out.println("\n\n\t\t\t 2: Esborrar Client ");
            System.out.println("\n\n\t\t\t 3: Llistar Clients ");
            System.out.println("\n\n\t\t\t 4: Tornar enrere \n");
            System.out.println("\n-------------------------------------");

            System.out.print("Tria una opció: ");

            int opcions = OperacionsBanc.verifySoloNumerosInteger(sc);
            switch (opcions) {
                case 1:
                    new MenuInserirClient().inserirClient();
                    break;
                case 2:

                    existeixClient = gestorClients.buscarClient();
                    gestorClients.operacioRealitzada(existeixClient);
                    break;
                case 3: // sortir

                    gestorClients.llistaDeClients();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("No siguis cabezón i tria una opció bona\n");
                    break;
            }
        }
    }
}
