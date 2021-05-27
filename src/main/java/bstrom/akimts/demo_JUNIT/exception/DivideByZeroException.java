package bstrom.akimts.demo_JUNIT.exception;

public class DivideByZeroException extends RuntimeException {

    public DivideByZeroException() {
        super("Division par 0 impossible");
    }
}
