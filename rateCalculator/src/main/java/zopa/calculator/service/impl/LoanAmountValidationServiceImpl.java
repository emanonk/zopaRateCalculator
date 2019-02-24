package zopa.calculator.service.impl;

import zopa.calculator.error.RequestedAmountError;
import zopa.calculator.exception.RequestedAmountValidationException;
import zopa.calculator.service.api.LoanAmountValidationService;

import java.math.BigDecimal;

public class LoanAmountValidationServiceImpl implements LoanAmountValidationService {

    private static BigDecimal MAX_REQUESTED_AMOUNT = new BigDecimal("15000");
    private static BigDecimal MIN_REQUESTED_AMOUNT = new BigDecimal("1000");;
    private static BigDecimal ROUNDED_VALIDATION_AMOUNT = new BigDecimal("100");

    public LoanAmountValidationServiceImpl() {
    }

    @Override
    public BigDecimal convertAndValidateRequestedAmount(String requestedAmountStr) {

        BigDecimal requestedAmount;
        try {
            requestedAmount = new BigDecimal(requestedAmountStr);
        }catch (NumberFormatException nfe) {
            throw new RequestedAmountValidationException(RequestedAmountError.NUMBER_FORMAT.getErrorMessage());
        }

        if (requestedAmount.compareTo(MAX_REQUESTED_AMOUNT) > 0) {
            throw new RequestedAmountValidationException(RequestedAmountError.MAX_AMOUNT.getErrorMessage());
        }

        if(requestedAmount.compareTo(MIN_REQUESTED_AMOUNT) < 0) {
            throw new RequestedAmountValidationException(RequestedAmountError.MIN_AMOUNT.getErrorMessage());
        }

        if(requestedAmount.remainder(ROUNDED_VALIDATION_AMOUNT).compareTo(BigDecimal.ZERO) != 0){
            throw new RequestedAmountValidationException(RequestedAmountError.ROUNDED_AMOUNT.getErrorMessage());
        }
        return requestedAmount;
    }
}
