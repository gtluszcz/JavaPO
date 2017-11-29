package Lab5;


import Lab5.Exceptions.WrongColumnIndexException;
import Lab5.Exceptions.WrongColumnLabelException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static org.junit.Assert.*;

public class CSVReaderTest {
    @Test
    public void testCSVFiles() throws Exception{

        System.out.println("elec.csv");
        try {
            CSVReader reader = new CSVReader("elec.csv", ",", true);
            reader.printAll();
        } catch (IOException e){
            System.out.print(e.getMessage());
        }

        System.out.println("missing-values.csv");

        try {
            CSVReader reader = new CSVReader("missing-values.csv", ",", true);
            reader.printAll();
        } catch (IOException e){
            System.out.print(e.getMessage());
        }

        System.out.println("no-header.csv");

        try {
            CSVReader reader = new CSVReader("no-header.csv", ",", true);
            reader.printAll();
        } catch (IOException e){
            System.out.print(e.getMessage());
        }

        System.out.println("with-header.csv");

        try {
            CSVReader reader = new CSVReader("with-header.csv", ",", true);
            reader.printAll();
        } catch (IOException e){
            System.out.print(e.getMessage());
        }

        System.out.println("accelerator.csv");

        try {
            CSVReader reader = new CSVReader("accelerator.csv", ";", true);
            reader.printAll();
        } catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

    @Test
    public void testGetNumberMethods() throws Exception{
        String text = "a,b,c\n123,567.8,9101112";
        CSVReader reader = new CSVReader(new StringReader(text),",",true);
        reader.next();
        assertEquals(123, reader.getLong("a"));
        assertEquals(567.8, reader.getDouble("b"),0.001);
        assertEquals(9101112, reader.getLong("c"));

    }

    @Test
    public void testNonDefinedIndexAndColumn() throws Exception{
        boolean columnexcpetioncaught = false;
        boolean indexexceptioncaught = false;


        String text = "a,b,c\n,567.8,9101112";
        CSVReader reader = new CSVReader(new StringReader(text),",",true);
        reader.next();
        try{
            reader.get(20);
        }catch(WrongColumnIndexException e){
            indexexceptioncaught = true;
        }

        try{
            reader.get("d");
        }catch (WrongColumnLabelException e){
            columnexcpetioncaught=true;
        }

        assertEquals(true, indexexceptioncaught);
        assertEquals(true, columnexcpetioncaught);

    }

    @Test
    public void testMissingFields() throws Exception{

        boolean wrongfieldsnumber = false;

        String text = "a,b,c\n123,567.8,9101112\n123,456";
        CSVReader reader = new CSVReader(new StringReader(text),",",true);
        try{
            reader.printAll();
        }catch (WrongColumnIndexException e){
            wrongfieldsnumber = true;
        }


    }
}
