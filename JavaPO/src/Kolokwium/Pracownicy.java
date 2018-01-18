package Kolokwium;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Pracownicy {
    List<Pracownik> pracownicy = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Pracownicy lista = new Pracownicy();
            lista.read("info_pracownicy.csv");

            lista.specjaliści();
            System.out.print("\n");
            lista.z_umowa();
            System.out.print("\n");
            System.out.print("\n");
            lista.inni();


        } catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

    public void read(String filename) throws IOException {
        CSVReader2 reader = new CSVReader2(filename,";",true);

        while(reader.next()){
            Pracownik on = new Pracownik();

            //populate adminUnit fields
            if (!reader.isMissing("Imię")){
                on.imie = reader.get("Imię");
            }
            if (!reader.isMissing("Nazwisko ")){
                on.nazwisko = reader.get("Nazwisko ");
            }
            if (!reader.isMissing("Stanowisko")){
                on.stanowisko = reader.get("Stanowisko");
            }
            if (!reader.isMissing("Forma zatrudnienia")){
                on.forma_zatrudnienia = reader.get("Forma zatrudnienia");
            }


            this.pracownicy.add(on);
        }



    }
    public void list(PrintStream out){
        for (Pracownik on: pracownicy) {
            out.append(on.toString() + "\n");
        }
    }

    public void specjaliści(){
        for (Pracownik on: pracownicy){

            if (on.stanowisko.contains("specjalista")) {
                System.out.print("Imię:"+on.imie + "  Nazwisko:" + on.nazwisko+"\n");
            }

        }
    }

    public void z_umowa(){
        int kobiety=0;
        int mezczyzni=0;
        for (Pracownik on: pracownicy){

            if (on.forma_zatrudnienia.matches("umowa o pracę")) {
                if(on.imie.endsWith("a")){kobiety+=1;}
                else{mezczyzni+=1;}
            }

        }
        System.out.print("Umowę o prace posiada "+kobiety+" kobiet i "+mezczyzni+" mężczyzn");
    }

    public void inni(){
        for (Pracownik on: pracownicy){

            if (!on.stanowisko.contains("specjalista") && !on.stanowisko.contains("asystent") && !on.stanowisko.contains("sekretarka") && !on.stanowisko.contains("stażysta")) {
                System.out.print("Imię:"+on.imie + "  Nazwisko:" + on.nazwisko+" Stanowisko:" + on.stanowisko+"\n");
            }

        }
    }

}
