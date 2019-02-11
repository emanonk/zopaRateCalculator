package zopa.calculator.service

import spock.lang.Specification
import zopa.calculator.domain.Lender
import zopa.calculator.domain.Quote
import zopa.calculator.error.RequestedAmountError
import zopa.calculator.exception.RequestedAmountException
import zopa.calculator.service.api.LenderService
import zopa.calculator.service.api.LoanCalculatorService
import zopa.calculator.service.impl.LenderServiceImpl
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


    def "Lender service returns the lenders with the lowest rates successfully"() {
        given: "a list of Lenders and a requested amount for a loan quote"

        when: "the lenders service is called to find the lowest rates"
        def monthlyPayment = loanCalculatorService.calculateMonthlyPayment(QUOTE)


        then: "a list of lenders with the lowest rates is returned"
        monthlyPayment == new BigDecimal("30.78")

    }

    def "Total repayment " () {
        //TODO details
        given: "a list of Lenders and a requested amount for a loan quote"
        QUOTE.setMonthlyRepayment(new BigDecimal("30.78"))

        when: "the lenders service is called to find the lowest rates"
        def total = loanCalculatorService.calculateTotalRepayment(QUOTE)


        then: "a list of lenders with the lowest rates is returned"
        total == new BigDecimal("1108.04")

    }

    def "total rate"() {
        //TODO details
        given: "a list of Lenders and a requested amount for a loan quote"
        QUOTE.setMonthlyRepayment(new BigDecimal("30.78"))
        QUOTE.setTotalRepayment(new BigDecimal("1108.04"))

        when: "the lenders service is called to find the lowest rates"
        def total = loanCalculatorService.calculateTotalRate(QUOTE)


        then: "a list of lenders with the lowest rates is returned"
        total == new BigDecimal("0.07")

    }


}