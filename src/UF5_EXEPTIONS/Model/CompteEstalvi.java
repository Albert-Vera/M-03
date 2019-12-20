package UF5_EXEPTIONS.Model;

import UF5_EXEPTIONS.AccountIncorrecta;
import UF5_EXEPTIONS.Account_Overdraft;
import UF5_EXEPTIONS.OperacionsBanc;
import UF5_EXEPTIONS.Transfer_Error;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static UF5_EXEPTIONS.ExceptionMessage.ACCOUNT_NOT_FOUND;
import static UF5_EXEPTIONS.ExceptionMessage.ACCOUNT_OVERDRAFT;

public class CompteEstalvi {
    private String numCompte;
    private int id = 0;
    private double saldo;
    public static List<Client>  llista_usuaris  ;

    public CompteEstalvi(String numCompte) {
        this.numCompte = numCompte;
        this.id = 1;
        saldo = 0;

    }

    public CompteEstalvi() {
    }

    /**
     Afegeix un usuari d'aquest compte
     @return quantitat d'usuaris que té el compte

      *
      * @param client
     * @param llista_usuaris*/
    public int addUser(Client client) {

        llista_usuaris.add(client);
        return llista_usuaris.size();
    }

    /**
     Elimina un usuari d'aquest compte
     @param dni
     @return quantitat d'usuaris que té el compte
     @throws BankAccountException
     **/
    public int removeUser(String dni) {

        llista_usuaris.removeIf(u -> dni.equals(u.getDNI()));
        return llista_usuaris.size();
    }

    public String  introdueixNumeroCompte(Scanner sc) throws AccountIncorrecta, IOException {
        String numeroCompte = "";
        System.out.print("Introdueix Número de Compte: ");
        numeroCompte = sc.nextLine();
        setNumCompte(numeroCompte);

        if (!OperacionsBanc.verifyNumeroCompte(numeroCompte)){
            throw new AccountIncorrecta (ACCOUNT_NOT_FOUND);
        }
        return numeroCompte;
    }
    public Double hiHaProuSaldo(Scanner sc, String numeroCompte) throws Account_Overdraft, IOException, Transfer_Error {

        Double diners = OperacionsBanc.verifyQuantitatCorrecta(sc, "retirar");

        if (!OperacionsBanc.verifySaldoSuficient(diners, numeroCompte)){
            throw new Account_Overdraft(ACCOUNT_OVERDRAFT);
        }
        return diners;
    }


    /**
     * Afegeix m diners al saldo
     * @param m
     */
    public void ingressar(double m) {
        saldo += m;
    }

    /**
     * Treu m diners del compte si n'hi han suficient
     * @param m
     * @throws
     */
    public void treure(double m) {
        saldo -= m;
    }

    public int getId() {
        return id ;
    }

    public void addId(int i) {
        this.id += i;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Client> getLlista_usuaris(List<Client> llista_usuaris) {
        return llista_usuaris;
    }
}
