package Lab5.Exceptions;

public class WrongColumnLabelException extends RuntimeException{
    public WrongColumnLabelException() {
        super("Column label not found");
    }
}
