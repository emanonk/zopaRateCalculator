package zopa.calculator.domain;

import java.math.BigDecimal;
import java.util.List;

public class Quote {

    private BigDecimal requestedAmount;
    //private LenderService lenderService;
    private List<Lender> lenders;
    private BigDecimal rate;
    private BigDecimal monthlyRepayment;
    private BigDecimal totalRepayment;

    public Quote(BigDecimal requestedAmount, List<Lender> lenders) {
        this.requestedAmount = requestedAmount;
        this.lenders = lenders;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public List<Lender> getLenders() {
        return lenders;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }

    public void setTotalRepayment(BigDecimal totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }

    @Override
    public String toString() {
        return "Requested Amount: £" + requestedAmount + "\n" +
                "Rate:" + rate + "% \n" +
                "Monthly Repayments: £" + monthlyRepayment + " \n" +
                "Total Repayment: £" + totalRepayment;
    }
}
