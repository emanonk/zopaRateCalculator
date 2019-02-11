package zopa.calculator.service

import spock.lang.Specification
import spock.lang.Unroll
import zopa.calculator.error.RequestedAmountError
import zopa.calculator.exception.RequestedAmountException
import zopa.calculator.service.impl.LoanAmountValidationServiceImpl

class LoanAmountValidationServiceImplSpec extends Specification {

    def loanAmountValidationService

    def setup() {
        loanAmountValidationService = new LoanAmountValidationServiceImpl()
    }

    @Unroll("No validation error when the requested loan amount is the #scenario allowed")
    def "No validation error when the requested loan amount is valid"() {
        when: "the validation service is called"
        loanAmountValidationService.convertAndValidateRequestedAmount(amount)

        then: "no exception is thrown"
        noExceptionThrown()

        where:
        scenario  | amount
        'minimum' | "1000"
        'maximum' | "15000"
    }

    @Unroll("Validation service throws an error when the requested amount #scenario")
    def "Validation service checks if the amount is valid"() {

        when: "the validation service is called"
        loanAmountValidationService.convertAndValidateRequestedAmount(amount)

        then: "an exception is thrown"
        def exception = thrown RequestedAmountException

        and: "with an appropriate error message"
        exception.getMessage() == errorMessage

        where:
        scenario                                 | amount    | errorMessage
        'is more that 15000'                     | "15001"   | RequestedAmountError.MAX_AMOUNT.getErrorMessage()
        'is less that 1000'                      | "999"     | RequestedAmountError.MIN_AMOUNT.getErrorMessage()
        'contains alphabet characters'           | "20AA"    | RequestedAmountError.NUMBER_FORMAT.getErrorMessage()
        'is not rounded to the nearest hundred'  | "1099"    | RequestedAmountError.ROUNDED_AMOUNT.getErrorMessage()
    }
}