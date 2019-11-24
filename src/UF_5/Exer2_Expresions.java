package UF_5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exer2_Expresions {

    private static String[] inLine = new String[4];
    private String[] busqueda = new String[3];

    public String[] getInLine() {
        return inLine;
    }

    public void setInLine(String[] inLine) {
        this.inLine = inLine;
    }

    public Exer2_Expresions(String[] casa) {

        busqueda[0] = "\\*<]:-DOo";
        busqueda[1] = ">:o\\)";
        busqueda[2] = "<]:-D";
        inLine[0] = "[0]-=-=*<]:-DOo##=========";
        inLine[1] = "]-=-*<]:-DOo--*=][=>:o)*=**<]:-DOo0-!...";
        inLine[2] = "-=-0-o<]:Oo|=--=||++=++=++=>";
        inLine[3] = "==|<]:-D";

    }

    public static void main(String[] args) {

        Exer2_Expresions exercici2 = new Exer2_Expresions(inLine);
        exercici2.buquedaPorLinea(exercici2);



    }

    public  void buquedaPorLinea(Exer2_Expresions cosas){


        boolean visitadoPor ;

        for (int i = 0; i < 4 ; i++) {  // FOR DE LINEAS DE ENTRADA (CASAS)

            visitadoPor = false;
            enQueCasaEstamos(i);
            visitadoPor = busquedaPersonajes(cosas, i, visitadoPor);

            if (!visitadoPor){
                System.out.println("\t\t\tNo ha rebut visites");
            }

        }
        System.out.println("Finito");
    }

    boolean busquedaPersonajes(Exer2_Expresions cosas, int i, boolean visitadoPor){

        for (int j = 0; j < 3; j++) {    // FOR DE CARACTERS PER LINEA

            Pattern patron = Pattern.compile(busqueda[j]);
            Matcher encaja = patron.matcher(inLine[i]);

            int contador = 0;

            while (encaja.find()) {
                contador++;
            }
            inLine[i] = encaja.replaceAll("");
            //System.out.println(contador);

            if (contador > 0){
                quePersonaje(contador, j);
                visitadoPor = true;
            }
            // System.out.println(inLine[i]);

        }
        return visitadoPor;
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
        System.out.println("\nLa " + casa + " casa es visitada por: ");
    }

    void quePersonaje(int contador, int s){
        String personaje, vez;

        switch (s){

            case 0:
                personaje = "Pare Nöel";
                break;
            case 1:
                personaje = "Rens del Pare Nöel";
                break;
            case 2:
                personaje = "Follets ajudants";
                break;
            default:
                return ;
        }

        if (contador > 1){
            vez = " veces";
        } else vez = " vez";
        System.out.println("\t\t\t" + personaje + " entro en la casa " + contador + vez);
    }
}
