package co.atlvntis.atlantida.exceptions;

public class MalformedCommandLineException extends RuntimeException {

    public MalformedCommandLineException(String message) {
        super(message, new Throwable(message), true, true);
    }

}
