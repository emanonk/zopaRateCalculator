package zopa.calculator.exception;

public class LendersDataFileException extends RuntimeException {

    public LendersDataFileException() {
    }

    public LendersDataFileException(String message) {
        super(message);
    }

    public LendersDataFileException(Throwable cause) {
        super(cause);
    }

    public LendersDataFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
