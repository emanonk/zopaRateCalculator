package zopa.calculator.controller;

import zopa.calculator.domain.Lender;
import zopa.calculator.domain.Quote;
import zopa.calculator.utilities.QuoteBuilder;
import zopa.calculator.exception.ApplicationException;
import zopa.calculator.service.api.LenderService;
import zopa.calculator.service.api.LendersCsvReaderService;
import zopa.calculator.service.api.LoanAmountValidationService;
import zopa.calculator.service.api.LoanCalculatorService;
import zopa.calculator.service.impl.LenderServiceImpl;
import zopa.calculator.service.impl.LendersCsvReaderServiceImpl;
import zopa.calculator.service.impl.LoanAmountValidationServiceImpl;
import zopa.calculator.service.impl.LoanCalculatorServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class MainController {

    private static final int NUMBER_OF_ARGUMENTS = 2;
    private String lendersDataFilePath;
    private String requestedAmountStr;
    private LendersCsvReaderService lendersCsvReaderService;
    private LoanAmountValidationService loanAmountValidationService;
    private LenderService lenderService;
    private LoanCalculatorService loanCalculatorService;

    public MainController() {
        this.lendersCsvReaderService = new LendersCsvReaderServiceImpl();
        this.loanAmountValidationService = new LoanAmountValidationServiceImpl();
        this.lenderService = new LenderServiceImpl();
        this.loanCalculatorService = new LoanCalculatorServiceImpl();
    }

    public String execute(String[] args) {

        validateCommandLineArgs(args);
        readAndAssignArguments(args);

        BigDecimal requestedAmount = loanAmountValidationService.convertAndValidateRequestedAmount(requestedAmountStr);

        List<Lender> lenders = lendersCsvReaderService.loadLendersDataFromFile(lendersDataFilePath);

        List<Lender> lendersWithLowestRates;

        try {
            lendersWithLowestRates = lenderService.findLendersWithLowestRates(lenders, requestedAmount);
        } catch (ApplicationException e) {
            return e.getMessage();
        }

        BigDecimal monthlyPayment = loanCalculatorService.calculateMonthlyPayment(lendersWithLowestRates);

        BigDecimal totalRepayment = loanCalculatorService.calculateTotalRepayment(monthlyPayment);

        BigDecimal totalRate = loanCalculatorService.calculateTotalRate(lendersWithLowestRates);

        Quote quote = new QuoteBuilder()
                .withRequestedAmount(requestedAmount)
                .withLenders(lendersWithLowestRates)
                .withMonthlyPayment(monthlyPayment)
                .withTotalRepayment(totalRepayment)
                .withRate(totalRate)
                .build();

        return quote.toString();

    }


    private void validateCommandLineArgs(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
            throw new RuntimeException("Invalid numbers of arguments, please pass the Lander's data file path and the requested amount");
        }
    }

    private void readAndAssignArguments(String[] args) {
        this.lendersDataFilePath = args[0];
        this.requestedAmountStr = args[1];
    }
}
