package UF_1;


import java.util.Arrays;
import java.util.Comparator;

public class CompararLlapissos implements Comparator<Llapis>{

   public int compare(Llapis llapis, Llapis t1) {

                if (llapis.getGruix() > t1.getGruix()){
                    return 1;
                }else{
                    if (llapis.getGruix() < t1.getGruix()){
                        return -1;
                    }else return 0;
                }

            }




}
