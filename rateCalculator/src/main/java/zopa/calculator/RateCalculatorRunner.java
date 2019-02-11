package zopa.calculator;

import zopa.calculator.controller.MainController;

public class RateCalculatorRunner {


    private static final int NUMBER_OF_ARGUMENTS = 2;


    public static void main(String[] args) {
        validateCommandLineArgs(args);
        new MainController(args).execute();

    }

    private static void validateCommandLineArgs(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
            throw new RuntimeException("Invalid numbers of arguments");
        }
    }
}
