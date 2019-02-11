package zopa.calculator.service

import spock.lang.Specification
import zopa.calculator.domain.Lender
import zopa.calculator.error.RequestedAmountError
import zopa.calculator.exception.RequestedAmountException
import zopa.calculator.service.api.LenderService
import zopa.calculator.service.impl.LenderServiceImpl

class LenderServiceImplSpec extends Specification {

    LenderService lenderService
    private static List<Lender> LENDERS  = [new Lender("Bob", new BigDecimal("0.075"), new BigDecimal("640")),
                          new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")),
                          new Lender("Fred", new BigDecimal("0.071"), new BigDecimal("60")),
                          new Lender("Mary", new BigDecimal("0.104"), new BigDecimal("170")),
                          new Lender("John", new BigDecimal("0.081"), new BigDecimal("320")),
                          new Lender("Dave", new BigDecimal("0.074"), new BigDecimal("140")),
                          new Lender("Angela", new BigDecimal("0.071"), new BigDecimal("520"))]


    def setup() {
        lenderService = new LenderServiceImpl()
    }


    def "Lender service returns the lenders with the lowest rates successfully"() {
        given: "a list of Lenders and a requested amount for a loan quote"

        when: "the lenders service is called to find the lowest rates"
        def requiredLenders = lenderService.findLendersWithLowestRates(LENDERS, new BigDecimal("1000"))


        then: "a list of lenders with the lowest rates is returned"
        requiredLenders.size() == 3
        requiredLenders[0].name == 'Jane'
        requiredLenders[0].rate == BigDecimal.valueOf(0.069)
        requiredLenders[0].amount == BigDecimal.valueOf(480)
        requiredLenders[1].name == 'Fred'
        requiredLenders[1].rate == BigDecimal.valueOf(0.071)
        requiredLenders[1].amount == BigDecimal.valueOf(60)
        requiredLenders[2].name == 'Angela'
        requiredLenders[2].rate == BigDecimal.valueOf(0.071)
        requiredLenders[2].amount == BigDecimal.valueOf(460)

    }

    def "Not enough available "() {
        given: "a list of Lenders and a requested amount that all the lenders cannot cover"

        when: "the lenders service is called to find the lowest rates"
        lenderService.findLendersWithLowestRates(LENDERS, new BigDecimal("14000"))

        then: "an exception is thrown to inform that system is not possible to provide a quote"
        def exception = thrown RequestedAmountException

        and: "with an appropriate message"
        exception.getMessage() == RequestedAmountError.NOT_ENOUGH_CAPITAL.getErrorMessage()
    }
}