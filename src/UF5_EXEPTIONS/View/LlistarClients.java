package UF5_EXEPTIONS.View;

import UF5_EXEPTIONS.Model.Client;
import UF5_EXEPTIONS.Model.CompteEstalvi;

public class LlistarClients {
    public void cabeceraClients(){

        System.out.println("\t\t\t\t\t----------------------------------------------------------");
        System.out.println("\t\t\t\t\t---           Llistat de Clients                       ---");
        System.out.println("\t\t\t\t\t----------------------------------------------------------");
        System.out.println("\t\t\tId      Nom         Cognoms         Dni              Nº Compte          Saldo");
        System.out.println("\t\t----------------------------------------------------------------------------");

    }
    public void showClients(Client client, CompteEstalvi compteEstalvi){

            System.out.println("\t\t\t" + compteEstalvi.getId() + "\t\t" + client.getNom() + "\t\t" +
                    client.getCognoms() + "\t\t" + client.getDNI() + "\t\t" + compteEstalvi.getNumCompte() + "\t\t"
                    + compteEstalvi.getSaldo());
    }
}
