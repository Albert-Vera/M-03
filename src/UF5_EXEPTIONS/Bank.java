package UF5_EXEPTIONS;

import UF5_EXEPTIONS.Manager.GestorClients;
import UF5_EXEPTIONS.Model.CompteEstalvi;
import UF5_EXEPTIONS.View.MenuInicial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class Bank {
    private static Scanner sc = new Scanner(System.in);
    public static final String SEP = ",";
    public Bank() {
        //new CompteEstalvi("SH92-40400017329") ;

    }

    public static void main(String[] args) throws DNIincorrectoExeption, IOException, Account_ZeroUser, Ingres_Erroni {
        CompteEstalvi compteEstalvi = new CompteEstalvi();
        GestorClients gestorClients = new GestorClients();
        compteEstalvi.llista_usuaris = new ArrayList<>();
        gestorClients.compteList = new ArrayList<>();

        new MenuInicial().menuBank();
    }




}
