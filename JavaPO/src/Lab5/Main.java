package Lab5;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args){
        try {
            AdminUnitList lista = new AdminUnitList();
            lista.read("admin-units.csv");
            lista.fixAll();
//            lista.selectByName("^Andrychów$",true).list(System.out);
//            lista.sortInplaceByName().list(System.out, 0 ,100);
            lista.filter(a->a.name.startsWith("Ż"),0,10).list(System.out);





//            AdminUnit unit = lista.selectByName("^Kraków$",true).units.get(0);
//            lista.getNeighbors(unit, 0).list(System.out);

        } catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

}
