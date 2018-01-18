package Kolokwium;

import Lab5.Exceptions.EmptyBufferException;
import Lab5.Exceptions.EmptyFieldToNumberException;
import Lab5.Exceptions.WrongColumnLabelException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader2 {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String[] current;

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

    public CSVReader2(String filename, String delimiter, boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);

    }

    public CSVReader2(String filename, String delimiter) throws IOException{
        this(filename, delimiter, true);
    }

    public CSVReader2(String filename) throws IOException{
        this(filename, ",", true);
    }

    public CSVReader2(Reader reader, String delimiter, boolean hasHeader) throws IOException{
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();

    }


    void parseHeader() throws IOException{
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = split(line);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
            this.columnLabelsToInt.put(header[i], i);
            this.columnLabels.add(header[i]);
        }

    }

    boolean next() {
        String line;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            return false;
        }

        if (line == null) {
            this.current = null;
            return false;
        }
        current = split(line);
        return true;
    }

    List<String> getColumnLabels() {
        return columnLabels;
    }



    int labelToIndex(String columnLabel){
        if (!this.columnLabelsToInt.containsKey(columnLabel)){
            throw new WrongColumnLabelException();
        }
        else{
            return this.columnLabelsToInt.get(columnLabel);
        }
    }


    public boolean isMissing(int columnIndex) {
        return columnIndex >= this.getRecordLength() || current[columnIndex].isEmpty();
    }



    boolean isMissing(String columnLabel) {
        return isMissing(labelToIndex(columnLabel));
    }



    String get(int columnIndex) {
        if(columnIndex<0 || this.isMissing(columnIndex)){
            return "";
        }
        return this.current[columnIndex];
    }



    String get(String columnLabel) {
        return this.get(labelToIndex(columnLabel));
    }




    protected String[] split(String line) {
        String[] splitLine = line.split(this.delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        for (int i = 0; i < splitLine.length; i++) {
            splitLine[i] = this.trimQuotes(splitLine[i]);
        }

        return splitLine;
    }

    protected String trimQuotes(String str) {
        return str.replaceAll("^\"", "").replaceAll("\"$", "");
    }

    String getTime(String time, String pattern){
        LocalTime timeresult = LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
        return timeresult.toString();
    }

    String getDate(String time, String pattern){
        LocalDate date = LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern));
        return date.toString();
    }

    int getRecordLength() {
        if (this.current!=null){
            return this.current.length;

        }
        else{
            throw new EmptyBufferException();
        }
    }

}
