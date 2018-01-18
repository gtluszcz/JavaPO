package Lab12;

public class Main {
    public static void main(String[] args){
        Mean.initArray(128000000);
        try {
            Mean.parallelMean(128);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
