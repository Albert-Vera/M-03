package UF_5;

import java.lang.reflect.Array;

public class Exer1_Expresions {

    public String[] getInLine() {
        return inLine;
    }

    public void setInLine(String[] inLine) {
        this.inLine = inLine;
    }

    private String[] inLine = new String[4];
    private String[] busqueda = new String[3];
    private static String[] casa = new String[5];
    private String pareNoel;
    private String rens ;
    private String follets ;
    private String perro ;



    public String[] getCasa() {
        return casa;
    }

    public void setCasa(String[] casa) {


    }


    public Exer1_Expresions(String[] casa) {
        casa[0] = "[0]-=-=****=][=========";
        casa[1] = "]-=-=****=][=*****=**=*![---!000";
        casa[2] = "=*0-!";
        casa[3] = "-=-0-oOo|=--=||++=++=++=>";
        casa[4] = "pepe";
        busqueda[0] = "*<]:-DOo";
        busqueda[1] = ">:o)";
        busqueda[2] = "<]:-D";
        inLine[0] = "[0]-=-=*<]:-DOo##=========";
        inLine[1] = "]-=-*<]:-DOo--*=][=>:o)*=**<]:-DOo0-!...";
        inLine[2] = "-=-0-o<]:Oo|=--=||++=++=++=>";
        inLine[3] = "==|<]:-D";

    }

    public static void main(String[] args) {

        Exer1_Expresions cosas = new Exer1_Expresions(casa);
        cosas.compararCasas(cosas);



    }

    public  void compararCasas(Exer1_Expresions cosas){

        char caracteAnterior, quienEsAnterior;
        int coincidencias, posChar, poscadena1;
        boolean visitadaPor ;


        for (int i = 0; i < 4 ; i++) {  // FOR DE LINEAS DE ENTRADA (CASAS)
            coincidencias = 0; posChar = 0; poscadena1 = 0;
            caracteAnterior =' ';
            quienEsAnterior =' ';
            enQueCasaEstamos(i);
            visitadaPor = false;

            for (int x = 0; x < 3; x++) {       // FOR DE PERSONATGES
                visitadaPor = busquedaPersonajes(cosas, x,i, coincidencias, posChar, poscadena1, caracteAnterior, quienEsAnterior, visitadaPor);
            }

            if (!visitadaPor){
                System.out.println("\t\t\tNo ha rebut visites");
            }
        }
        System.out.println("Finito");
    }

    boolean busquedaPersonajes(Exer1_Expresions cosas, int x, int i, int coincidencias, int posChar, int poscadena1, char caracteAnterior, char quienEsAnterior, boolean visitadaPor){

        char caracter, quienEs;
        String cadenaA, cadenaB;

        for (int j = 0; j < cosas.inLine[i].length(); j++) {    // FOR DE CARACTERS PER LINEA
            caracter = cosas.inLine[i].charAt(j);               // LLEGIR PRIMER CHAR DE LINEA ENTRADA
            quienEs = busqueda[x].charAt(posChar);         // PRIMER CHAR DE PERSONATGE

            // if caracter es Repeteix i quienEs no es repeteix.. resetea quienEs
            if (caracter == caracteAnterior && quienEs != quienEsAnterior) {
                posChar = 0;
                coincidencias = 0;      // D'AQUESTA MANERA TORNAR A COMENÇAR
                quienEs = busqueda[x].charAt(posChar);
            }
            // COMPARA CHAR PERSONATGE AMB LINEA ENTRADA
            if (caracter == quienEs && coincidencias == posChar) {

                if (posChar == 0){
                    poscadena1 = j;
                }
                coincidencias++;
                posChar++;
            } else {     // SI NO COINCIDEIX CHAR PERSONATGE TORNA A PRIMER CHAR
                coincidencias = 0;
                posChar = 0;
            }
            // SI TE COINCIDENCIA TOTAL
            if (coincidencias == cosas.busqueda[x].length()) {
                cadenaA = cosas.inLine[i].substring( 0,poscadena1 );
                cadenaB = cosas.inLine[i].substring( j+1, cosas.inLine[i].length());
                coincidencias = 0;
                posChar = 0;
                visitadaPor = true;
                quePersonaje(busqueda[x]);
                cosas.inLine[i] = cadenaA + cadenaB;
            }
            caracteAnterior = caracter;     // PER SOLUCIONAR PROBLEMA DE CARACTER REPETITS A LA ENTRADA
            quienEsAnterior = quienEs;
        }
        return visitadaPor;
    }

    void enQueCasaEstamos(int x){
        String casa;

        switch (x){
            case 0:
                casa = "primera";
                break;
            case 1:
                casa = "segunda";
                break;
            case 2:
                casa = "tercera";
                break;
            case 3:
                casa = "cuarta";
                break;
            default: return ;
        }
        System.out.println("La " + casa + " casa es visitada por: ");
    }

    void quePersonaje(String s){
        String personaje;

        switch (s){

            case "*<]:-DOo":
                personaje = "Pare Nöel";
                break;
            case ">:o)":
                personaje = "Rens del Pare Nöel";
                break;
            case "<]:-D":
               personaje = "Follets ajudants";
                break;
            default: personaje = "\t\t\t Ninguna visita ";
            return ;
        }
        System.out.println("\t\t\t" + personaje);
    }
}
