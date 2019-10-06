package UF_1.Juegos.Dados;

import javax.swing.*;
import java.util.Scanner;

public class MenuDados {

    String nl = System.getProperty("line.separator");

    public MenuDados() {

        while (true) {

            String texto =("          ##############      JUEGO DE DADOS    ##################"
                    + nl + nl
                    + nl + "  (1)  Tirar dados"
                    + nl + "  (0)  Terminar partida"  + nl);
            String opcion = JOptionPane.showInputDialog(texto);

            switch (opcion) {

                case "0":
                    terminar();

                    return;
                case "1":
                    new JocdeDaus().JocdeDaus();
                    break;

                default:
                    texto="INTRODUCE SOLO UNA DE LAS DOS OPCIONES";
                    Daus.imprimir(texto);
                    break;
            }
        }
    }

    public void terminar(){

        String texto =("Partidas Ganadas:  " + JocdeDaus.partidasGanadas
                + nl + "Partidas Perdidas:  " + JocdeDaus.partidasPerdidas

                + nl + "Partidas Jugadas:  " + JocdeDaus.contador
                + nl );
        Daus.imprimir(texto);

        JocdeDaus.contador = 0;
        JocdeDaus.partidasGanadas = 0;
        JocdeDaus.partidasPerdidas = 0;

    }
}
