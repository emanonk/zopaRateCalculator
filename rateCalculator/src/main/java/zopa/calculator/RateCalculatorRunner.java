package zopa.calculator;

import zopa.calculator.controller.MainController;
import zopa.calculator.domain.Quote;

public class RateCalculatorRunner {


    private static final int NUMBER_OF_ARGUMENTS = 2;


    public static void main(String[] args) {
        validateCommandLineArgs(args);
        Quote quote = new MainController(args).execute();

        System.out.println(quote.toString());

    }

    private static void validateCommandLineArgs(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
            throw new RuntimeException("Invalid numbers of arguments, please pass the Lander's data file path and the requested amount");
        }
    }
}
