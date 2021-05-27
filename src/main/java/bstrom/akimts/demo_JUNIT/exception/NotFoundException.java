package bstrom.akimts.demo_JUNIT.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("L'élément n'a pas été trouvé");
    }
}
