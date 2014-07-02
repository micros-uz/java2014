package uz.micros;


public class ArgumentException extends RuntimeException {
    public ArgumentException(IndexOutOfBoundsException e) {
        super(e);
    }
}
