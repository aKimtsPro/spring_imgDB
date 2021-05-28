package bstrom.akimts.demo_imgDB.exception;

public class DivideByZeroException extends RuntimeException {

    public DivideByZeroException() {
        super("Division par 0 impossible");
    }
}
