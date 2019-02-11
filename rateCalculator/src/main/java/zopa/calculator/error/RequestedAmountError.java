package zopa.calculator.error;

public enum RequestedAmountError {
    MAX_AMOUNT("Requested amount is too big, must be between £1,000 and £150,000"),
    MIN_AMOUNT("Requested amount is too small, must be between £1,000 and £150,000"),
    ROUNDED_AMOUNT("Requested amount must be rounded to the nearest hundred"),
    NUMBER_FORMAT("A loan amount as a second parameter must be a number. ex. 4000"),
    NOT_ENOUGH_CAPITAL("It is not possible to provide a quote at this time.");

    private String errorMessage;
    RequestedAmountError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
