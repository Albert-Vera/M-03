package UF5_EXEPTIONS;


import UF5_EXEPTIONS.Model.CompteEstalvi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static UF5_EXEPTIONS.Bank.SEP;
import static UF5_EXEPTIONS.ExceptionMessage.INGRES_ERRONI;
import static UF5_EXEPTIONS.ExceptionMessage.TRANSFER_ERROR;

public class OperacionsBanc {


    public static boolean verifyDNI(String nif) {
        //TODO implementar fòrnula de verificació del DNI


        Pattern patron = Pattern.compile("[0-9]{8}[A-Z]");
        Matcher matcher = patron.matcher(nif);

        if (matcher.find()) {
            return true;
        }else return false;

        // AQUI HI HA EL MÉTODE DE VALIDAR EL DNI CORRECTE, PERO ESTA DESACTIVAT PER PROVES


//        System.out.println("NIF "+nif);
//        //si es NIE, eliminar la x,y,z inicial para tratarlo como nif
//        if (nif.toUpperCase().startsWith("X")||nif.toUpperCase().startsWith("Y")||nif.toUpperCase().startsWith("Z"))
//            nif = nif.substring(1);
//
//        Pattern nifPattern =
//                Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
//        Matcher m = nifPattern.matcher(nif);
//        if(m.matches()){
//            String letra = m.group(2);
//            //Extraer letra del NIF
//            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
//            int dni = Integer.parseInt(m.group(1));
//            dni = dni % 23;
//            String reference = letras.substring(dni,dni+1);
//
//            if (reference.equalsIgnoreCase(letra)){
//                System.out.println("son iguales. Es NIF. "+letra+" "+reference);
//                return true;
//            }else{
//                System.out.println("NO son iguales. NO es NIF. "+letra+" "+reference);
//                return false;
//            }
//        }
//        else
//            return false;

    }

    public static void transferencia(CompteEstalvi font, CompteEstalvi desti, double suma) {
        //TODO implementar transferència
    }

    public static boolean verifyNumeroCompte(String numeroCompte) throws IOException {
        boolean compteTrobat = false;

        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;

        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);

            if (values[4].equalsIgnoreCase(numeroCompte)) {
                compteTrobat = true;
            }
        }
        inputStream.close();

        if (compteTrobat) return true;
        else return false;

    }

    /**
     *  Verifica que no s'ingresin quantitats negativas
     * @param sc        Variable Scanner
     * @param money     quantitat diners
     * @return
     */
    public static Double verifyQuantitatCorrecta(Scanner sc, String texte){
        boolean repetir = false;
        Double money = 0.0;

        while ( !repetir) {
            try {
                System.out.print("introdueix la quantitat que vols " + texte + " : ");
                money = sc.nextDouble();

                if (money < 0 ){
                    throw new Ingres_Erroni(INGRES_ERRONI);
                } else repetir = true;
            }catch (Ingres_Erroni e){
                System.out.println("\n----------------------------------------------");
                System.out.println("\n\t\t La quantitat introduida no és correcta");
                System.out.println("----------------------------------------------\n");
            }
        }
        return money;
    }

    public static int verifySoloNumeros (Scanner sc){

        boolean repetir= false;
        String texto = "";
        int numero = 0;

        while (!repetir) {
            try {
                texto = sc.nextLine();
                numero = Integer.parseInt(texto);
                repetir = true;
            } catch (NumberFormatException e) {
                System.out.println("Ese valor no es válido");
            }
        }
        return numero;
    }

    public static boolean verifySaldoSuficient (Double diners, String numeroCompte) throws IOException, Transfer_Error {

        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;

        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);

            if (values[4].equalsIgnoreCase(numeroCompte)) {

                if ( diners <= (Double.parseDouble(values[5]))) {
                    return true;
                }else throw new Transfer_Error(TRANSFER_ERROR);
            }
        }
        inputStream.close();
        return false;
    }

    public static boolean verifyZeroUser (String dni) throws IOException {

        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;

        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);

            if (values[3].equalsIgnoreCase(dni)) {

                if ( values.length < 7) {
                    return true;
                }
            }
        }
        inputStream.close();
        return false;
    }
}
