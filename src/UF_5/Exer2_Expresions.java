package UF_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exer2_Expresions {

    private String[] busqueda = new String[3];

    public String[] getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String[] busqueda) {
        this.busqueda = busqueda;
    }

    public Exer2_Expresions() {

        busqueda[0] = "\\*<]:-DOo";
        busqueda[1] = ">:o\\)";
        busqueda[2] = "<]:-D";
    }

    public static void main(String[] args) throws IOException {

        Exer2_Expresions exercici2 = new Exer2_Expresions();
        exercici2.llegir();
    }

    private void llegir() throws IOException {

        File leerFile = new File("/home/albert/IdeaProjects/M-03/src/UF_5/santako.txt");
        BufferedReader inputStream = new BufferedReader(new FileReader(leerFile));
        String linea;

        while((linea = inputStream.readLine()) != null && !linea.isEmpty()){
            buquedaPorLinea(linea);
        }
        inputStream.close();
    }

    public  void buquedaPorLinea(String linea){

        boolean visitadoPor ;
        visitadoPor = false;    // CONTROLAR SI NO HI HA VISITES
        visitadoPor = busquedaPersonajes(linea, visitadoPor);

        if (!visitadoPor){      // SI NO HI HA VISITES IMPRIMIRA NO HI HA VISITES
            System.out.println();
        }else{
            System.out.println();
        }
    }

    boolean busquedaPersonajes(String linea, boolean visitadoPor){    // BUSQUEDA PERSONATGES DINTRE LA LINEA DE ENTRADA

        for (int j = 0; j < 3; j++) {    // FOR DE CARACTERS PER LINEA

            Pattern patron = Pattern.compile(busqueda[j]);
            Matcher encaja = patron.matcher(linea);
            int contador = 0;

            while (encaja.find()) {
                contador++;
            }
            linea = encaja.replaceAll("");

            if (contador > 0){
                quePersonaje(contador, j);
                visitadoPor = true;
            }
        }
        return visitadoPor;
    }

    void quePersonaje(int contador, int s){     // DONAR VALOR STRING A PERSONATGE
        String personaje, vez;

        switch (s){
            case 0:
                personaje = "Pare Nöel ";
                break;
            case 1:
                personaje = "Ren ";
                break;
            case 2:
                personaje = "Follet ";
                break;
            default:
                return ;
        }
        System.out.print(personaje + "(" + contador + ")  ");
    }
}
