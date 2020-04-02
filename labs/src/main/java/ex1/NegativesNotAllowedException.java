package ex1;

public class NegativesNotAllowedException extends RuntimeException {

    NegativesNotAllowedException(String message) {
        super(message);
    }

}
