package UF_1.Juegos.Dados;

import javax.swing.*;

public class Daus {

    String texto;


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

    public static int getValor(){

        // aqui comparar valores
        int dad=0;

        return dad;
    }


}
