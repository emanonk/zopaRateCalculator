package zopa.calculator.controller

import spock.lang.Specification
import zopa.calculator.domain.Lender
import zopa.calculator.domain.Quote
import zopa.calculator.error.RequestedAmountError
import zopa.calculator.exception.RequestedAmountException
import zopa.calculator.service.api.LenderService
import zopa.calculator.service.api.LendersCsvReaderService
import zopa.calculator.service.api.LoanAmountValidationService
import zopa.calculator.service.api.LoanCalculatorService

class MainControllerSpec extends Specification {

    def mainController

    private static List<Lender> LENDERS  = [
            new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")),
            new Lender("Angela", new BigDecimal("0.071"), new BigDecimal("520"))]


    def setup() {
        mainController = new MainController(lendersDataFilePath:"a_file_path",
                requestedAmountStr: "1000",
                lendersCsvReaderService: Mock(LendersCsvReaderService),
                loanAmountValidationService: Mock(LoanAmountValidationService),
                lenderService: Mock(LenderService),
                loanCalculatorService: Mock(LoanCalculatorService))
    }

    def "The application runs successfully Req[04,05,06,07,08,09]"() {
        given: "a valid requested amount and enough lenders to cover that amount"

        when: "the main controller is called"
        Quote finalQuote = mainController.execute()

        then: "the services do the right calculation"
        1 * mainController.loanAmountValidationService.convertAndValidateRequestedAmount(_) >> new BigDecimal("1000")
        1 * mainController.lendersCsvReaderService.loadLendersDataFromFile(_)


        1 * mainController.lenderService.findLendersWithLowestRates(_, _) >> LENDERS
        1 * mainController.loanCalculatorService.calculateMonthlyPayment(_) >> BigDecimal.valueOf(38.78)
        1 * mainController.loanCalculatorService.calculateTotalRepayment(_) >> new BigDecimal("1108.10")
        1 * mainController.loanCalculatorService.calculateTotalRate(_) >> BigDecimal.valueOf(0.07)

        and: "the quote details are printed"
        finalQuote.toString() == "Requested amount: £1000\nRate: 7.0%\nMonthly repayment: £38.78\nTotal repayment: £1108.10"
    }

    def "There is not enough capital to provide a quote Req[13]"() {
        given: "a requested amount that the lenders cannot cover"

        when: "the main controller is called"
        mainController.execute()

        then: "an exception is thrown"
        def exception = thrown(RequestedAmountException)

        and:
        1 * mainController.loanAmountValidationService.convertAndValidateRequestedAmount(_)
        1 * mainController.lendersCsvReaderService.loadLendersDataFromFile(_)
        1 * mainController.lenderService.findLendersWithLowestRates(_, _) >> { throw new RequestedAmountException(RequestedAmountError.NOT_ENOUGH_CAPITAL.getErrorMessage()) }
        0 * mainController.loanCalculatorService.calculateMonthlyPayment(_)
        0 * mainController.loanCalculatorService.calculateTotalRepayment(_)
        0 * mainController.loanCalculatorService.calculateTotalRate(_)

        and: "with an appropriate message"
        exception.getMessage() == "It is not possible to provide a quote at this time."
    }

}