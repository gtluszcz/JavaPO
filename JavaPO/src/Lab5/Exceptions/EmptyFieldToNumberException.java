package Lab5.Exceptions;

public class EmptyFieldToNumberException extends RuntimeException {
    public EmptyFieldToNumberException() {
        super("Empty field cannot be converted to number");
    }
}
