package UF_5;

import java.io.*;

public class Exer1_NoExpresions {

    private String[] busqueda = new String[3];

    public String[] getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String[] busqueda) {
        this.busqueda = busqueda;
    }

    public Exer1_NoExpresions() {

        busqueda[0] = "*<]:-DOo";
        busqueda[1] = ">:o)";
        busqueda[2] = "<]:-D";
    }

    public static void main(String[] args) throws IOException {

        Exer1_NoExpresions datos = new Exer1_NoExpresions();

        File leerFile = new File("/home/albert/IdeaProjects/M-03/src/UF_5/santako.txt");
        BufferedReader inputStream = new BufferedReader(new FileReader(leerFile));
        String linea, cadenaA, cadenaB;

        while((linea = inputStream.readLine()) != null && !linea.isEmpty()){

            for (int i = 0; i < 3 ; i++) {      // FOR PER PERSONATGES
                int contador = 0;

                while (linea.indexOf(datos.busqueda[i]) > -1) { // ENTRA MENTRES TROBA PERSONATGES
                    cadenaA = linea.substring( 0, linea.indexOf(datos.busqueda[i]));  //agafa char de tal pos a tal pos
                    cadenaB = linea.substring( linea.indexOf(datos.busqueda[i]) + datos.busqueda[i].length(), linea.length());
                    linea = cadenaA + cadenaB;  // LINEA SENSE EL PERSONATGE TROBAT
                    contador++;
                }
                if (i == 0 && contador > 0 ) System.out.print("Pare Nöel ("+ contador + ") ");
                if (i == 1 && contador > 0 ) System.out.print("Rens (" + contador + ") ");
                if (i == 2 && contador > 0 ) System.out.print("Follets (" + contador + ") ");
            }
            System.out.println();
        }
        inputStream.close();
    }
}
