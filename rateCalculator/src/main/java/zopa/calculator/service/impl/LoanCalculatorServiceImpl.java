package zopa.calculator.service.impl;

import zopa.calculator.domain.Lender;
import zopa.calculator.domain.Quote;
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
    public BigDecimal calculateMonthlyPayment(Quote quote) {


        Optional<BigDecimal> reduce = quote.getLenders().stream().map(lender -> calculateMonthlyPaymentByLender(lender)).reduce(BigDecimal::add);




        System.out.println(reduce);

        //TODO
//        BigDecimal one = calculateMonthlyPaymentByLender(quote.getLenders().get(0));
//        System.out.println(one);
//
//        BigDecimal two = calculateMonthlyPaymentByLender(quote.getLenders().get(1));
//        System.out.println(two);
//
//        BigDecimal three = calculateMonthlyPaymentByLender(quote.getLenders().get(2));
//        System.out.println(three);

//        return three;
        System.out.println(getMonthlyPayments(480,new BigDecimal("0.069")));
        System.out.println(getMonthlyPayments(60,new BigDecimal("0.071")));
        System.out.println(getMonthlyPayments(460,new BigDecimal("0.071")));

//        return getMonthlyPayments(1000,new BigDecimal("7"));
        return reduce.orElse(BigDecimal.ZERO);
    }

    public static BigDecimal getMonthlyPayments(Integer requestedAmount, BigDecimal rate) {
        return getMonthlyRate(rate).multiply(BigDecimal.valueOf(requestedAmount))
                .divide(BigDecimal.valueOf(1)
                        .subtract((BigDecimal.valueOf(1)
                                .add(getMonthlyRate(rate))
                                .pow(-LOAN_LENGTH, MathContext.DECIMAL64))), RoundingMode.HALF_UP);
    }

    private static BigDecimal getMonthlyRate(BigDecimal rate) {
        BigDecimal mothlyrate = rate.divide(new BigDecimal(12), 100, RoundingMode.HALF_UP);
        //System.out.println("monthly rate:"+mothlyrate);
        return mothlyrate;
    }

    private BigDecimal calculateMonthlyPaymentByLender(Lender lender){

        BigDecimal monthlyRate = lender.getRate().divide(new BigDecimal("12"), 100, RoundingMode.FLOOR);

        //System.out.println("monthly rate:"+monthlyRate);

//        (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -termInMonths));
//        x = loanAmount*monthlyRate
//        y = 1+monthlyRate
//        z = y.pow(-36)
//        c = 1-z
//        monthlyPayment = x/c

        BigDecimal x = monthlyRate.multiply(lender.getAmount());
        BigDecimal y = BigDecimal.ONE.add(monthlyRate);
        BigDecimal z = y.pow(-LOAN_LENGTH, MathContext.DECIMAL64);
        BigDecimal c = BigDecimal.ONE.subtract(z);
        BigDecimal monthlyPayment = x.divide(c, RoundingMode.HALF_DOWN);

        BigDecimal round = monthlyPayment.setScale(2, RoundingMode.FLOOR);
        System.out.println("** rate:" + round);

        return round;
    }

    @Override
    public BigDecimal calculateTotalRepayment(Quote quote) {
        return quote.getMonthlyRepayment().multiply(new BigDecimal("36"));
    }

    @Override
    public BigDecimal calculateTotalRate(Quote quote) {
        BigDecimal requestedAmount = quote.getRequestedAmount();

        BigDecimal monthlyPaymentWithoutInterest = requestedAmount.divide(new BigDecimal("36"),4, RoundingMode.HALF_UP);

        BigDecimal monthlyRepayment = quote.getMonthlyRepayment();

        BigDecimal monthlyInterest = monthlyRepayment.subtract(monthlyPaymentWithoutInterest);


        BigDecimal finalPayment = quote.getTotalRepayment();


        BigDecimal rate = monthlyInterest.divide(finalPayment, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("12"));




        BigDecimal interest = finalPayment.subtract(requestedAmount);

        BigDecimal divide = interest.divide(new BigDecimal("36"), 10, RoundingMode.HALF_UP);

//        System.out.println(divide);



        return rate;
    }
}
