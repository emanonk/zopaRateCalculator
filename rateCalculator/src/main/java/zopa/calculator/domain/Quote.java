package zopa.calculator.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Quote {

    private BigDecimal requestedAmount;
    private List<Lender> lenders;
    private BigDecimal rate;
    private BigDecimal monthlyPayment;
    private BigDecimal totalRepayment;

    public Quote(BigDecimal requestedAmount, List<Lender> lenders) {
        this.requestedAmount = requestedAmount;
        this.lenders = lenders;
    }

    public List<Lender> getLenders() {
        return lenders;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setTotalRepayment(BigDecimal totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    private String printRate(){
        return rate.multiply(BigDecimal.valueOf(100)).setScale(1, RoundingMode.HALF_UP).toString();
    }

    @Override
    public String toString() {
        return "Requested amount: £" + requestedAmount + "\n" +
                "Rate: " + printRate() + "%\n" +
                "Monthly repayment: £" + monthlyPayment + "\n" +
                "Total repayment: £" + totalRepayment;
    }
}
