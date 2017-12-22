package Lab5;

import java.io.IOException;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args){
        try {
            AdminUnitList lista = new AdminUnitList();
            lista.read("admin-units.csv");
            lista.fixAll();

            //lista.selectByName("Rokietnica",false).sortInplaceByName().list(System.out);

            // wybór (i sortowanie) elementów zaczynających się na „K”
            //lista.filter(a->a.name.startsWith("K")).sortInplaceByName().list(System.out);

            //wybór jednostek będących powiatami, których parent.name to województwo małopolskie
            //lista.filter(a-> a.adminLevel == 6 && a.parent.name.matches("województwo małopolskie")).list(System.out);

            //wybór jednostek będących miejscowosciami, których obszar > 50
            //lista.filter(a-> a.adminLevel == 8 && a.area > 50).list(System.out);

            //wybór jednostek będących gminami, których populacja > 50000
            //lista.filter(a-> a.adminLevel == 7 && a.population > 50000).list(System.out);

            AdminUnitQuery query = new AdminUnitQuery()
                    .selectFrom(lista)
                    .where(a->a.area>1000)
                    .or(a->a.name.startsWith("Sz"))
                    .sort((a,b)->Double.compare(a.area,b.area))
                    .limit(100);
            query.execute().list(System.out);



        } catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

}
