package UF5_EXEPTIONS.Manager;


import UF5_EXEPTIONS.Account_ZeroUser;
import UF5_EXEPTIONS.Model.Client;
import UF5_EXEPTIONS.Model.CompteEstalvi;
import UF5_EXEPTIONS.DNIincorrectoExeption;
import UF5_EXEPTIONS.OperacionsBanc;
import UF5_EXEPTIONS.View.LlistarClients;
import UF5_EXEPTIONS.View.ShowOperacions;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static UF5_EXEPTIONS.ExceptionMessage.ACCOUNT_ZERO_USER;


public class GestorClients {

    Client client ;
    final String SEP = ",";

    public static List<CompteEstalvi> compteList ;
    static CompteEstalvi compteEstalvi ;
    private  Scanner sc = new Scanner(System.in);

    public GestorClients() throws FileNotFoundException {
    }

    /**
     *          Crea un compte Nou amb client Nou
     * @throws IOException
     */
    public void clientNouCompte() throws IOException {
        String dni= null;
        int id = llegirUltimId();
       // int cli = compteEstalvi.llista_usuaris.size()+1;
        System.out.print("Introdueix nom: ");
        String nom = sc.nextLine();
        System.out.print("Introdueix cognoms: ");
        String cognoms = sc.nextLine();
        boolean repetir = false;
        while (!repetir) {
            try {
                System.out.print("Introdueix Dni: ");
                dni = sc.nextLine();
                client = new Client(nom, cognoms, dni);
                repetir = true;
            } catch (DNIincorrectoExeption e) {
                // e.printStackTrace();
                System.out.println("\n\t\tIntrodueix un DNi valid");
            }
        }

        compteEstalvi =  new CompteEstalvi("SH92-404000173290"+id);

        compteList.add(compteEstalvi);
        //clientsUnCompteList.add(client);
       // cli =compteEstalvi.addUser(client);
        //compteEstalvi.llista_usuaris.add(client);
        writeFitxerComptes(compteEstalvi, client , id );
        System.out.println("Número de compte: " + compteEstalvi.getNumCompte());

    }

    /**
     *      Afegeix un client nou a compte ja existen
     *
     * @throws IOException
     * @throws DNIincorrectoExeption
     */
    public void clientCompteExisten() throws IOException, DNIincorrectoExeption {

        String dni = null;
        System.out.println("Introdueix: ");
        System.out.print("\t Número de compte: ");
        String num = sc.nextLine();

        if (!verificarExisteixCompte(num)) {
            System.out.println("NO ok ");

        } else {
            System.out.println("ok");
            System.out.print("Introdueix nom: ");
            String nom = sc.nextLine();
            System.out.print("\t\t cognoms: ");
            String cognoms = sc.nextLine();
            boolean repetir = false;

            while (!repetir) {
                try {
                    System.out.print("\t\t Dni: ");
                    dni = sc.nextLine();
                    client = new Client(nom, cognoms, dni);
                    repetir = true;
                } catch (DNIincorrectoExeption e) {
                    System.out.println("\n\t\tIntrodueix un DNi valid");
                }
            }
            modificarBaseDatos(client, num);
        }
    }


    /**
     *
     * @param clientNou Es el client que vols afegir a un compte existen
     * @param compteSolicitat Es el compte que has introduit on afegir nou client
     * @throws IOException
     */
    private void modificarBaseDatos(Client clientNou, String compteSolicitat) throws IOException{

        File destino = new File ("fitxerComptes.dat");
        File tmpFile = new File(destino.getAbsolutePath() + "tmp");

        BufferedReader inputStream = new BufferedReader(new FileReader(destino));
        BufferedWriter outputStream = new BufferedWriter(new FileWriter(tmpFile, false));
        String line;

        while((line = inputStream.readLine()) != null){
            String[] values = line.split(",");

            if (values[4].equalsIgnoreCase(compteSolicitat)){

                for (int i = 0; i < values.length; i++) {

                    outputStream.write(values[i] + SEP);
                }

                outputStream.write(clientNou.getNom() + SEP + clientNou.getCognoms() + SEP + clientNou.getDNI() + "\n");
            } else {
                outputStream.write(line + "\n");
            }
        }

        outputStream.close();
        inputStream.close();

        tmpFile.renameTo( new File("fitxerComptes.dat"));

    }

    public String esborrarClient() throws Account_ZeroUser, IOException {
        String dni = null;
        System.out.println("Introdueix el DNI client que vols esborrar  /  o pulsa Enter per tornar al menú");

        System.out.print("\t\t Dni: ");
        dni = sc.nextLine();

        if (OperacionsBanc.verifyZeroUser(dni)){
            throw new Account_ZeroUser(ACCOUNT_ZERO_USER);
        }

        return dni;
    }
    public boolean buscarClient() throws IOException, Account_ZeroUser {
        // quan esborris no esborris... les dades mai s'han de perdre...
        // posa-li NULL

        String dniAEliminar = "";
        boolean repetir = false;
        // verificar si hi ha prou saldo per retirar diners
        while (!repetir) {
            try {
                dniAEliminar = esborrarClient();
                repetir = true;
            }catch ( Account_ZeroUser e){
                System.out.println("----------------------------------------------");
                System.out.println("\n\t\t El compte no pot quedar amb Zero usuaris");
                System.out.println("\t\t Si ho desitges pots esborrar el compte");
                System.out.println("----------------------------------------------");
            }
        }

        File destinoBorrar = new File ("fitxerComptes.dat");
        File tmpFile = new File(destinoBorrar.getAbsolutePath() + "tmp");
        BufferedWriter outputStream2 = new BufferedWriter(new FileWriter(tmpFile, true));

        boolean existe = false;
        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;

        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);

            if (values.length > 6){
                if(!values[3].equalsIgnoreCase(dniAEliminar)){

                    for (int i = 8; i < values.length; i+=3) {
                        if(values[i].equalsIgnoreCase(dniAEliminar)){
                            // es igual
                            existe = true;
                            esborrarClientDelFitxer(i, false, values[0],outputStream2);
                        }
                    }
                    if (!existe){
                        esborrarClientDelFitxer(0, false, values[0],outputStream2);
                    }
                }else {
                    esborrarClientDelFitxer(3, false, values[0],outputStream2);
                    existe = true;
                }
            }else if(!values[3].equalsIgnoreCase(dniAEliminar)){
                esborrarClientDelFitxer(0,false, values[0],outputStream2);

            }else {
                        // Aixo es client únic i per tant no és pot esborrar
                esborrarClientDelFitxer(3, true , values[0],outputStream2);
                existe = true;
            }
        }
        outputStream2.close();

        tmpFile.renameTo(new File("fitxerComptes.dat"));
        inputStream.close();
        return existe;
    }

    public void operacioRealitzada(boolean existeixClient){

        if (existeixClient){
            System.out.println(" Operació realitzada amb exit");
            // falta imprimir veure clients del compte restants
        }else
            System.out.println("La operació no s'ha pogut realitzar");
    }

    /**
     * Aqui es tracta de esborrar un client de la linea del fitxer Comptes clients
     * Agafant de referencia la posicio on es trobo es DNI
     * Tenin em comte el nombre de clients per linea
     * @param posicion  Posicio ones troba el DNI al fitxer comptes clients
     * @param clientUnic
     */
    private void esborrarClientDelFitxer(int posicion, boolean clientUnic, String lineaCorrecta, BufferedWriter outputStream2) throws IOException {
        ShowOperacions showOperacions = new ShowOperacions();
        BufferedReader inputStream2 = new BufferedReader(new FileReader("fitxerComptes.dat"));

        String line;

        while((line = inputStream2.readLine()) != null ){
            String[] values = line.split(",");

            if (lineaCorrecta.equals(values[0])) {
                if (!clientUnic) {
                    if (posicion == 0) {
                        outputStream2.write(line + "\n");
                    }
                    if (posicion == 3) {
                        outputStream2.write(values[0] + SEP + values[6] + SEP + values[7] + SEP + values[8] + SEP + values[4] + SEP + values[5]);
                        for (int i = 9; i < values.length; i++) {

                            outputStream2.write(SEP + values[i]);
                        }
                        outputStream2.write("\n");
                    }
                    if (posicion > 6 && values.length > 9) {
                        outputStream2.write(values[0] + SEP + values[1] + SEP + values[2] + SEP + values[3] + SEP + values[4] + SEP + values[5]);

                        for (int i = 9; i < values.length; i++) {

                            outputStream2.write(SEP + values[i]);
                        }
                        outputStream2.write("\n");
                    }
                    if (posicion == 8 && values.length < 10) {
                        outputStream2.write(values[0] + SEP + values[1] + SEP + values[2] + SEP + values[3] + SEP + values[4] + SEP + values[5] + "\n");

                    }
                } else {
                    // no es pot esborrar client
                    showOperacions.showNoesPotEsborrar();

                }
            }
        }
        inputStream2.close();
    }

    /**
     * Llista tots el clients
     */
    public void llistaDeClients() throws IOException {
        LlistarClients llistarClients = new LlistarClients();
        llistarClients.cabeceraClients();
        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;
        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);

            Client client = new Client();
            CompteEstalvi compteEstalvi = new CompteEstalvi();
            compteEstalvi.setId(Integer.valueOf(values[0]));
            client.setNom(values[1]);
            client.setCognoms(values[2]);
            client.setDNI(values[3]);
            compteEstalvi.setNumCompte(values[4]);
            compteEstalvi.setSaldo(Double.parseDouble(values[5]));

            llistarClients.showClients( client, compteEstalvi);

            if (values.length > 6){

                for (int i = 6; i < values.length; i += 3) {
                    client.setNom(values[i]);
                    client.setCognoms(values[i + 1]);
                    client.setDNI(values[i + 2]);
                    llistarClients.showClients( client, compteEstalvi);
                }
            }
        }
        inputStream.close();
    }

    private void writeFitxerComptes(CompteEstalvi compteEstalvi, Client client, int id) throws IOException {

        BufferedWriter outputStream = new BufferedWriter(new FileWriter("fitxerComptes.dat", true));
        outputStream.write(id + SEP + client.getNom() + SEP + client.getCognoms()+ SEP + client.getDNI() +SEP
                + compteEstalvi.getNumCompte() + SEP + compteEstalvi.getSaldo() + "\n" );

        outputStream.close();
    }

    private int llegirUltimId() throws IOException {
        int ultimID = 0;
        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;
        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);
            ultimID = Integer.valueOf(values[0]);
            ultimID += 1;
        }
        inputStream.close();
        System.out.println(ultimID);
        return ultimID;
    }

    private boolean verificarExisteixCompte(String compteSolicitat) throws IOException {
        boolean existeix = false;
        BufferedReader inputStream = new BufferedReader(new FileReader("fitxerComptes.dat"));
        String line;
        while((line = inputStream.readLine()) != null){
            String[] values = line.split(SEP);
            if (values[4].equalsIgnoreCase(compteSolicitat)) {
                existeix = true;
            }
        }
        inputStream.close();

        return existeix;
    }
}
