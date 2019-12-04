package UF_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AlumneCallable {

    static class Examinarse implements Callable<Integer> {
        private int nota;

        public Examinarse(int nota) {
            this.nota = nota;

        }
        @Override
        public Integer call() throws Exception {
            return nota;
        }
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        List<Examinarse> llistanotas= new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Examinarse calcula = new Examinarse((int)(Math.random()*10));
            llistanotas.add(calcula);
        }
        List <Future<Integer>> llistaResultats;
        llistaResultats = executor.invokeAll(llistanotas);

        executor.shutdown();
        for (int i = 0; i < llistaResultats.size(); i++) {
            Future<Integer> resultat = llistaResultats.get(i);
            try {
                System.out.println("Resultat alumne "+i+ " és:" + resultat.get());
            } catch (InterruptedException | ExecutionException e) {
            }
        }

    }
}
