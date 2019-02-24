package zopa.calculator.service.impl;

import zopa.calculator.domain.Lender;
import zopa.calculator.service.api.LoanCalculatorService;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class LoanCalculatorServiceImpl implements LoanCalculatorService {

    private static final Integer LOAN_LENGTH = 36;

    public LoanCalculatorServiceImpl() {
    }

    @Override
    public BigDecimal calculateMonthlyPayment(List<Lender> lenders) {

        Optional<BigDecimal> reduce = lenders.stream().map(this::calculateMonthlyPaymentByLender).reduce(BigDecimal::add);

        return reduce.orElseThrow(RuntimeException::new);
    }

    private BigDecimal calculateMonthlyPaymentByLender(Lender lender){

        BigDecimal monthlyRate = lender.getRate().divide(BigDecimal.valueOf(12), 100, RoundingMode.CEILING);

//        (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -termInMonths));
//        x = loanAmount*monthlyRate
//        y = 1+monthlyRate
//        z = y.pow(-36)
//        c = 1-z
//        monthlyPayment = x/c

        BigDecimal x = monthlyRate.multiply(lender.getAmount(),MathContext.DECIMAL64);
        BigDecimal y = BigDecimal.ONE.add(monthlyRate);
        BigDecimal z = y.pow(-LOAN_LENGTH, MathContext.DECIMAL64);
        BigDecimal c = BigDecimal.ONE.subtract(z);
        BigDecimal monthlyPayment = x.divide(c, RoundingMode.FLOOR);

        return monthlyPayment.setScale(2, RoundingMode.FLOOR);
    }

    @Override
    public BigDecimal calculateTotalRepayment(BigDecimal monthlyPayment) {
        return monthlyPayment.multiply(BigDecimal.valueOf(36));
    }

    @Override
    public BigDecimal calculateTotalRate(List<Lender> lenders) {

        Optional<BigDecimal> reduce = lenders.stream().map(lender -> lender.getRate().multiply(lender.getAmount()).divide(BigDecimal.valueOf(12),100, RoundingMode.CEILING)).reduce(BigDecimal::add);

        BigDecimal finalRate = reduce.orElseThrow(RuntimeException::new).divide(BigDecimal.valueOf(1000),100, RoundingMode.CEILING).multiply(BigDecimal.valueOf(12));

        return finalRate.setScale(2, RoundingMode.HALF_UP);
    }
}
