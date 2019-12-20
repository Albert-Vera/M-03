package UF5_EXEPTIONS.View;

import UF5_EXEPTIONS.Account_ZeroUser;
import UF5_EXEPTIONS.DNIincorrectoExeption;
import UF5_EXEPTIONS.Ingres_Erroni;
import UF5_EXEPTIONS.Manager.GestorOperacions;
import UF5_EXEPTIONS.OperacionsBanc;

import java.io.IOException;
import java.util.Scanner;

public class MenuInicial {

    public void menuBank() throws IOException, DNIincorrectoExeption, Account_ZeroUser, Ingres_Erroni {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n-------------------------------------");
            System.out.println("\n\t\t\t\t Banc Tim Ador ");
            System.out.println("\n\n\t\t\t 1: Gestió de Clients ");
            System.out.println("\n\n\t\t\t 2: Operacions ");
            System.out.println("\n\n\t\t\t 3: Sortir \n");
            System.out.println("\n-------------------------------------");

            int opcions = OperacionsBanc.verifySoloNumerosInteger(sc);
            switch (opcions) {
                case 1:
                    new MenuGestorClients().menuGestor();
                    break;
                case 2: // operacions
                    String numeroCompte = new GestorOperacions().introdueixCompte();
                    new ShowOperacions().menuOperacions(numeroCompte);
                    break;
                case 3: // sortir
                    return;
                default:
                    break;
            }
        }
    }
}
