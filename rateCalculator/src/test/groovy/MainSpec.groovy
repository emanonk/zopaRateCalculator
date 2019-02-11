
import spock.lang.Specification

class MainSpec extends Specification {

    def "The application runs successfully"() {
        quoteDescription = "Requested amount: £1000\nRate: 7.0%\nMonthly repayment: £38.78\nTotal repayment: £1108.10"

    }


    def "There is not enough capital to provide a quote"() {

                quoteDescription.equals("It is not possible to provide a quote at this time.")
    }

    def "The application parameters are invalid"() {
        when:
        BigDecimal amount = new BigDecimal("asd")

        then:
        true
    }
}