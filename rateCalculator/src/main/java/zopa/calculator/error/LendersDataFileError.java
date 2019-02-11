package zopa.calculator.error;

public enum LendersDataFileError {
    FILE_NOT_FOUND("Lender file cannot be found"),
    CORRUPTED_FILE("Some data are missing from the file, format should be: name,rate,amount"),
    NUMBER_FORMAT("Some data in the file are invalid, the rate and the amount must be numbers");

    private String errorMessage;
    LendersDataFileError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
