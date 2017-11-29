package Lab5.Exceptions;

public class WrongColumnIndexException extends RuntimeException{
    public WrongColumnIndexException() {
        super("Column index not found");
    }
}
