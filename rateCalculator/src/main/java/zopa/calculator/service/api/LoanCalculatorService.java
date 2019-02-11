package zopa.calculator.service.api;

import zopa.calculator.domain.Quote;
import java.math.BigDecimal;

public interface LoanCalculatorService {

    BigDecimal calculateMonthlyPayment(Quote quote);

    BigDecimal calculateTotalRepayment(Quote quote);

    BigDecimal calculateTotalRate(Quote quote);


}
