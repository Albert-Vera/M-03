package UF_1.Juegos.Dados;

import javax.swing.*;

import static UF_1.Juegos.Dados.JocdeDaus.*;

public class Daus {

    public static int tirar(){

        // aconseguir valor

        int dado;
        dado = (int)(Math.random()*6 + 1);

        return dado;
    }

    public static void imprimir(String texto){

        //Lanzamos el mensaje:

        JOptionPane.showMessageDialog(null, texto);

    }

    public static void getValor(){

        // aqui comparar valores

        String texto;

        if ((dado1 == dado2) && (dado2 == dado3)){

            partidasGanadas++;
            texto = " !!!!!!   ESTUPENDO GANASTE !!!!!!";
            Daus.imprimir(texto);
        }else{

            partidasPerdidas++;
            texto = "  !!!!!!!!  ERES DECEPCIONANTE..... PERDISTE   !!!!!!!";
            Daus.imprimir(texto);
        }
    }
}
