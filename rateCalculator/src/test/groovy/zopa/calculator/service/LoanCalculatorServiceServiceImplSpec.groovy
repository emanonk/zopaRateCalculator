package zopa.calculator.service

import spock.lang.Specification
import zopa.calculator.domain.Lender
import zopa.calculator.domain.Quote
import zopa.calculator.service.api.LoanCalculatorService
import zopa.calculator.service.impl.LoanCalculatorServiceImpl

class LoanCalculatorServiceServiceImplSpec extends Specification {

    LoanCalculatorService loanCalculatorService
    private static List<Lender> LENDERS  = [
                          new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")),
                          new Lender("Fred", new BigDecimal("0.071"), new BigDecimal("60")),
                          new Lender("Angela", new BigDecimal("0.071"), new BigDecimal("460"))]

    private Quote QUOTE = new Quote(new BigDecimal("1000"), LENDERS)

    def setup() {
        loanCalculatorService = new LoanCalculatorServiceImpl()
    }


    def "Monthly payment is calculated and returned successfully"() {
        given: "a list of Lenders and a requested amount for a loan quote"

        when: "the calculate monthly payment service is called"
        def monthlyPayment = loanCalculatorService.calculateMonthlyPayment(QUOTE)

        then: "the monthly payment amount is returned"
        monthlyPayment == new BigDecimal("30.78")

    }

    def "Total repayment is calculated and returned successfully" () {
        given: "the monthly payment has been calculated"
        QUOTE.setMonthlyPayment(new BigDecimal("30.78"))

        when: "the calculate total repayment service is called"
        def total = loanCalculatorService.calculateTotalRepayment(QUOTE)

        then: "the total repayment amount is returned"
        total == new BigDecimal("1108.04")
    }

    def "Total rate is calculated and returned successfully"() {
        given: "a list of Lenders and a requested amount for a loan quote"

        when: "the calculate final total rate service is called"
        def total = loanCalculatorService.calculateTotalRate(QUOTE)

        then: "the total final rate is returned"
        total == new BigDecimal("0.07")
    }


}