package zopa.calculator.utilities;

import zopa.calculator.domain.Lender;
import zopa.calculator.domain.Quote;

import java.math.BigDecimal;
import java.util.List;

public class QuoteBuilder {

    private BigDecimal requestedAmount;
    private List<Lender> lenders;
    private BigDecimal rate;
    private BigDecimal monthlyPayment;
    private BigDecimal totalRepayment;

    public QuoteBuilder() {
    }

    public QuoteBuilder withRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
        return this;
    }

    public QuoteBuilder withLenders(List<Lender> lenders) {
        this.lenders = lenders;
        return this;
    }

    public QuoteBuilder withRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    public QuoteBuilder withMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
        return this;
    }

    public QuoteBuilder withTotalRepayment(BigDecimal totalRepayment) {
        this.totalRepayment = totalRepayment;
        return this;
    }

    public Quote build() {
        Quote finalQuote = new Quote();
        finalQuote.setRequestedAmount(requestedAmount);
        finalQuote.setLenders(lenders);
        finalQuote.setRate(rate);
        finalQuote.setMonthlyPayment(monthlyPayment);
        finalQuote.setTotalRepayment(totalRepayment);
        return finalQuote;
    }
}
