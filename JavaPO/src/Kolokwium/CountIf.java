package Kolokwium;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class CountIf {
    static int[] array;

    static BlockingQueue<Integer> results = new ArrayBlockingQueue<Integer>(100);


    /**
     * Uzupełnia tablice losowymi intami.
     * @param size - rozmiar tablicy do uzupełnienia
     */

    static void initArray(int size){
        array = new int[size];
        Random r =new Random();
        for(int i=0;i<size;i++){
            array[i]= r.nextInt(100);
        }
    }

    public static void main(String[] args) {
        initArray(100000000);
        try {
                parallelCalc(4,a->a<10);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class CountIfCalc extends Thread{
        private final int start;
        private final int end;
        int ilosc = 0;

        CountIfCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(Predicate<Integer> pred){
            // ??? liczymy średnią
            ilosc=0;
            for (int l=start; l<=end; l++){
                if (pred.test(array[l])){
                    ilosc+=1;
                }
            }
            try {
                results.put(ilosc);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(Locale.US,"%d-%d ilosc spełaniających warunek=%d\n",start,end,ilosc);
        }
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     * @param pred - warunek zliczenia
     */
    static void parallelCalc(int cnt, Predicate<Integer> pred) throws InterruptedException {
        // utwórz tablicę wątków
        CountIfCalc threads[]=new CountIfCalc[cnt];
        int iel = array.length/cnt;
        for(int i=0;i<cnt;i++){
            // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
            // załóż, że array.length dzieli się przez cnt)
            CountIfCalc mc = new CountIfCalc(i*iel,(i+1)*iel-1);
            threads[i]=mc;
        }

        double t1 = System.nanoTime()/1e6;
        //uruchom wątki
        for(CountIfCalc mc:threads) {
            mc.run(pred);
        }
        double t2 = System.nanoTime()/1e6;
        int ilosc=0;
        for(int i=0;i<cnt;i++){
            ilosc+=results.take();
        }
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f ilosc spełaniających warunek=%d\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                ilosc);
    }
}
