package Lab11;

import java.time.LocalTime;

public class Clock extends Thread{



    @Override
    public void run() {
        while (true){
            LocalTime time = LocalTime.now();
            System.out.printf("%02d:%02d:%02d\n",
                    time.getHour(),
                    time.getMinute(),
                    time.getSecond());
        }
    }

    public static void main(String[] args) {
        new Clock().run();

    }
}