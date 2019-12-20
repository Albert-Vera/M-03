package UF5_EXEPTIONS.Model;


import UF5_EXEPTIONS.DNIincorrectoExeption;
import UF5_EXEPTIONS.OperacionsBanc;

import static UF5_EXEPTIONS.ExceptionMessage.WRONG_DNI;

public class Client {
    private String Nom;
    private String Cognoms;
    private String DNI;

    public Client() {
    }

    public Client(String nom, String cognoms, String DNI) throws DNIincorrectoExeption {
        Nom = nom;
        Cognoms = cognoms;

        if(OperacionsBanc.verifyDNI(DNI)){
            this.DNI = DNI;
        }else{
         throw new DNIincorrectoExeption (WRONG_DNI);

        }

    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCognoms() {
        return Cognoms;
    }

    public void setCognoms(String cognoms) {
        Cognoms = cognoms;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Nom='" + Nom + '\'' +
                ", Cognoms='" + Cognoms + '\'' +
                ", DNI='" + DNI + '\'' +
                '}';
    }
}
