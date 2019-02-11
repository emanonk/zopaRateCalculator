package zopa.calculator

import spock.lang.Specification
import spock.lang.Unroll


class RateCalculatorRunnerSpec extends Specification {

    @Unroll("The application throws an exception when #scenario are passed Req[01]")
    def "The application validates the numbers of the passed arguments"() {
        when:" the application is called"
        RateCalculatorRunner.main(arguments.toArray() as String[])

        then: "an exception is throw"
        def exception = thrown RuntimeException

        and: "with appropriate message"
        exception.getMessage() == "Invalid numbers of arguments, please pass the Lander's data file path and the requested amount"

        where:
        scenario     | arguments
        "no args"    | []
        "one arg"    | ["file path"]
        "three args" | ["arg1", "arg2", "arg3"]
    }
}