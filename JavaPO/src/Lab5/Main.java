package Lab5;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args){
        try {
            AdminUnitList list = new AdminUnitList();
            list.read("admin-units.csv");
            list.list(System.out,120000,-10);
        } catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

}
