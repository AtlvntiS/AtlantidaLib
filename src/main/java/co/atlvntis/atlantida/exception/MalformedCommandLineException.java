package co.atlvntis.atlantida.exception;

public class MalformedCommandLineException extends RuntimeException {

    public MalformedCommandLineException(String message) {
        super(message, new Throwable(message), true, true);
    }

}
