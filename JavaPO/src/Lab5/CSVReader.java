package Lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    /**
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    /**
     * @param nazwy kolumn w takiej kolejności, jak w pliku
     * @param odwzorowanie: nazwa kolumny -> numer kolumny
     */

    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        this(filename,delimiter);

        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    public CSVReader(String filename, String delimiter) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
    }

    public CSVReader(String filename) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
    }


    void parseHeader() throws IOException {

        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
        }

    }
}
