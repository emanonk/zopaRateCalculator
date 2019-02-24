package zopa.calculator.exception;

public class RequestedAmountValidationException extends RuntimeException {

    public RequestedAmountValidationException() {
    }

    public RequestedAmountValidationException(String message) {
        super(message);
    }

    public RequestedAmountValidationException(Throwable cause) {
        super(cause);
    }

    public RequestedAmountValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
