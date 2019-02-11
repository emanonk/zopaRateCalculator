package zopa.calculator.service.api;

import java.math.BigDecimal;

public interface LoanAmountValidationService {

    BigDecimal convertAndValidateRequestedAmount(String requestedAmountStr);
}
