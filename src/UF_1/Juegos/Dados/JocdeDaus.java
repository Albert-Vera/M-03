package UF_1.Juegos.Dados;

import javax.swing.*;

import static UF_1.Juegos.Dados.Daus.tirar;

public class JocdeDaus {

    public static int dado1;
    public static int dado2;
    public static int dado3;
    public static int contador;
    public static int partidasGanadas;
    public static int partidasPerdidas;

    public void JocdeDaus(){

        int contador, partidasGanadas, partidasPerdidas;

        dado1 = 0;
        dado2 = 0;
        dado3 = 0;
        jugar();
    }

    public void jugar(){

        dado1 = Daus.tirar();
        dado2 = Daus.tirar();
        dado3 = Daus.tirar();
        contador++;

        String nl = System.getProperty("line.separator");
        String texto =("   DADOS LANZADOS   "
                + nl + "DADO 1:   " + dado1

                + nl + "DADO 2:   " + dado2
                + nl + "DADO 3:   " + dado3);

        Daus.imprimir(texto);

        Daus.getValor();

    }
}
