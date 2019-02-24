package zopa.calculator.service.api;

import zopa.calculator.domain.Lender;
import zopa.calculator.domain.Quote;
import java.math.BigDecimal;
import java.util.List;

public interface LoanCalculatorService {

    BigDecimal calculateMonthlyPayment(List<Lender> lenders);

    BigDecimal calculateTotalRepayment(BigDecimal monthlyPayment);

    BigDecimal calculateTotalRate(List<Lender> lenders);


}
