package zopa.calculator.exception;

public class RequestedAmountException extends RuntimeException {

    public RequestedAmountException() {
    }

    public RequestedAmountException(String message) {
        super(message);
    }

    public RequestedAmountException(Throwable cause) {
        super(cause);
    }

    public RequestedAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
