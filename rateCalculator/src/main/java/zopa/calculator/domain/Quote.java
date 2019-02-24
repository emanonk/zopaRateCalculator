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

    public Quote() {
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public List<Lender> getLenders() {
        return lenders;
    }

    public void setLenders(List<Lender> lenders) {
        this.lenders = lenders;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(BigDecimal totalRepayment) {
        this.totalRepayment = totalRepayment;
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
