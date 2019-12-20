package UF5_EXEPTIONS.View;

import UF5_EXEPTIONS.Ingres_Erroni;
import UF5_EXEPTIONS.Model.CompteEstalvi;
import UF5_EXEPTIONS.Manager.GestorOperacions;
import UF5_EXEPTIONS.OperacionsBanc;

import java.io.IOException;
import java.util.Scanner;

public class ShowOperacions {

   public void menuOperacions(String numeroCompte) throws IOException, Ingres_Erroni {
        Scanner sc = new Scanner(System.in);
       CompteEstalvi compteEstalvi = new CompteEstalvi();

        while(true){
            System.out.println("\n-------------------------------------");
            System.out.println("\n\t\t\t\t Operacions ");
            System.out.println("\n\n\t\t\t 1: Saldo Disponible ");
            System.out.println("\n\n\t\t\t 2: Ingresar");
            System.out.println("\n\n\t\t\t 3: Retirar Diners");
            System.out.println("\n\n\t\t\t 4: Transferencias ");
            System.out.println("\n\n\t\t\t 5: Tornar enrere \n");
            System.out.println("\n-------------------------------------");
            System.out.print("Tria una opció: ");
            int opcions = OperacionsBanc.verifySoloNumeros(sc);

            switch (opcions) {
                case 1:

                    new GestorOperacions().veureSaldo(numeroCompte, compteEstalvi);

                    break;
                case 2:
                    new GestorOperacions().ingresarDiners(numeroCompte, compteEstalvi);
                    break;
                case 3:
                    new GestorOperacions().treureDiners(compteEstalvi);
                    // ;
                    break;
                case 4: // sortir
                    new GestorOperacions().ferTransferencia("SH92-4040001732904", "SH92-4040001732905", compteEstalvi);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("No siguis cabezón i tria una opció bona\n");
                    break;
            }
        }
    }

    public void showSaldo(CompteEstalvi compteEstalvi){
        System.out.println("\n\n\t\t\t\t\t----------------------------------------------------------");
        System.out.println("\t\t\t\t\t---           Operacions d'efectiu                       ---");
        System.out.println("\t\t\t\t\t----------------------------------------------------------");
        System.out.println("\n Nº de Compte: " + compteEstalvi.getNumCompte() + "\t\t\t Saldo disponible:    " + compteEstalvi.getSaldo());
    }

    public void showNoesPotEsborrar(){
        System.out.println("\n------------------------------------------------");
        System.out.println(" NO ES POT ESBORRAR ES UN CLIENT UNIC AL COMPTE");
        System.out.println("------------------------------------------------\n");

    }
    public void showTransferencia(){
        System.out.println("\n\n\t\t\t\t\t----------------------------------------------------------");
        System.out.println("\t\t\t\t\t---              Transferencias                        ---");
        System.out.println("\t\t\t\t\t----------------------------------------------------------");
    }
}
