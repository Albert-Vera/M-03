package UF5_EXEPTIONS.Manager;

import UF5_EXEPTIONS.*;
import UF5_EXEPTIONS.Model.CompteEstalvi;
import UF5_EXEPTIONS.View.ShowOperacions;

import java.io.*;
import java.util.Scanner;

import static UF5_EXEPTIONS.Bank.SEP;


public class GestorOperacions {
    Scanner sc = new Scanner(System.in);
    ShowOperacions operacions = new ShowOperacions();
    CompteEstalvi compteEstalvi = new CompteEstalvi();

    public String introdueixCompte(){
        String numeroCompte ="";
        boolean repetir = false;
        // verificar si existeix número de compte
        while (!repetir) {
            try {
                numeroCompte = compteEstalvi.introdueixNumeroCompte(sc);
                repetir = true;
            }catch (AccountIncorrecta | IOException e){

                System.out.println("\n----------------------------------------------");
                System.out.println("\n\t\t Introdueix un número de compte vàlid");
                System.out.println("----------------------------------------------\n");
            }
        }
        return numeroCompte;
    }

    public void ingresarDiners(String numeroCompte, CompteEstalvi compteEstalvi) throws IOException, Ingres_Erroni {

        Double money = 230.0;

        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;
        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);

            if (values[4].equalsIgnoreCase(numeroCompte)) {
                money = OperacionsBanc.verifyQuantitatCorrecta(sc, "ingresar");

                compteEstalvi.setNumCompte(values[4]);
                compteEstalvi.setSaldo(money + (Double.parseDouble(values[5])));
                operacions.showSaldo(compteEstalvi);
                modificarBaseDades(values[3], compteEstalvi);
            }
        }
        inputStream.close();

    }

    public void treureDiners(CompteEstalvi compteEstalvi) throws IOException {
        String numeroCompte="";
        Double diners = 0.0;

        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;
        boolean repetir = false;
        // verificar si existeix número de compte
        while (!repetir) {
            try {
                numeroCompte = compteEstalvi.introdueixNumeroCompte(sc);
                repetir = true;
            }catch (AccountIncorrecta e){

                System.out.println("\n----------------------------------------------");
                System.out.println("\n\t\t Introdueix un número de compte vàlid");
                System.out.println("----------------------------------------------\n");
            }
        }
        repetir = false;
        // verificar si hi ha prou saldo per retirar diners
        while (!repetir) {
            try {
                diners = compteEstalvi.hiHaProuSaldo(sc, numeroCompte);
                repetir = true;
            }catch (Account_Overdraft | Transfer_Error e){
                System.out.println("----------------------------------------------");
                System.out.println("\n\t\t No tens prou diners en el compte");
                System.out.println("----------------------------------------------");
            }
        }

        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);


            if (values[4].equalsIgnoreCase(numeroCompte)) {
                compteEstalvi.setNumCompte(values[4]);
                compteEstalvi.setSaldo((Double.parseDouble(values[5])) - diners);
                operacions.showSaldo(compteEstalvi);
                String dni = values[3];
                modificarBaseDades(dni, compteEstalvi);
            }
        }
        inputStream.close();
    }

    /**
     *
     * @param numeroCompte1 Els numeros de compte li arriven al cridar al métode desde el menu
     * @param numeroCompte2  Son valors de proba
     * @param compteEstalvi
     * @throws IOException
     */

    public void ferTransferencia(String numeroCompte1, String numeroCompte2, CompteEstalvi compteEstalvi) throws IOException, Ingres_Erroni {
       Double transferencia = 0.0;
        boolean repetir = false;
        System.out.println("\n Introdueix el número de compte origen de la trasferència\n");
        numeroCompte1 = introdueixCompte();
        System.out.println("\n Introdueix el número de compte desti de la trasferència\n");
        numeroCompte2 = introdueixCompte();

        while (!repetir) {
            try {
                transferencia = OperacionsBanc.verifyQuantitatCorrecta(sc, "transferir");
                OperacionsBanc.verifySaldoSuficient(transferencia, numeroCompte1);
                repetir = true;
            } catch (Transfer_Error transfer_error) {
                System.out.println("\n----------------------------------------------");
                System.out.println("\n\t\t Tranferència errónea, no hi ha prou saldo");
                System.out.println("----------------------------------------------\n");
            }
        }
        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;
        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);

            if (values[4].equalsIgnoreCase(numeroCompte1)) {
                compteEstalvi.setNumCompte(values[4]);
                compteEstalvi.setSaldo( (Double.parseDouble(values[5])) - transferencia);
                operacions.showSaldo(compteEstalvi);
                String dni = values[3];
                modificarBaseDades(dni, compteEstalvi);
            }
            if (values[4].equalsIgnoreCase(numeroCompte2)) {
                compteEstalvi.setNumCompte(values[4]);
                compteEstalvi.setSaldo( (Double.parseDouble(values[5])) + transferencia);
                operacions.showSaldo(compteEstalvi);
                String dni = values[3];
                modificarBaseDades(dni, compteEstalvi);
            }
        }
        inputStream.close();

    }

    public void veureSaldo(String numeroCompte, CompteEstalvi compteEstalvi) throws IOException {

        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;
        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);
            if (values[4].equalsIgnoreCase(numeroCompte)) {
                compteEstalvi.setNumCompte(values[4]);
                compteEstalvi.setSaldo(Double.parseDouble(values[5]));
                operacions.showSaldo(compteEstalvi);

            }
        }
        inputStream.close();

    }

    /**
     * Modificar el fitxer on s'emmagatzement totes les dades
     * Cada ingres, retirade de diners, eliminar client,
     * en cada moviment es modificar la base de dades del fitxer
     *
     * @param dni
     * @param compteEstalvi
     * @throws IOException
     */
    private void modificarBaseDades(String dni , CompteEstalvi compteEstalvi) throws IOException {
        File destino = new File ("fitxerComptes.dat");
        File tmpFile = new File(destino.getAbsolutePath() + "tmp");

        BufferedReader inputStream = new BufferedReader(new FileReader(destino));
        BufferedWriter outputStream = new BufferedWriter(new FileWriter(tmpFile, false));
        String line;

        while((line = inputStream.readLine()) != null){
            String[] valor = line.split(",");

            if (valor[3].equalsIgnoreCase(dni)){ // Si es el dni correcta modificar la linea del fitxer dades

                for (int i = 0; i < 5; i++) {

                    outputStream.write(valor[i] + SEP);
                }

                outputStream.write(String.valueOf(compteEstalvi.getSaldo()));

                for (int e = 6; e < valor.length; e++) {

                    outputStream.write( SEP + valor[e] );
                }
                outputStream.write( "\n");
            } else {
                outputStream.write(line + "\n");
            }
        }

        outputStream.close();
        inputStream.close();

        tmpFile.renameTo( new File("fitxerComptes.dat"));
    }
}
