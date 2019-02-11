package zopa.calculator.controller;

import zopa.calculator.domain.Lender;
import zopa.calculator.domain.Quote;
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

    private String lendersDataFilePath;
    private String requestedAmountStr;
    private LendersCsvReaderService lendersCsvReaderService;
    private LoanAmountValidationService loanAmountValidationService;
    private LenderService lenderService;
    private LoanCalculatorService loanCalculatorService;

    public MainController(String[] args) {
        this.lendersDataFilePath = args[0];
        this.requestedAmountStr = args[1];
        this.lendersCsvReaderService = new LendersCsvReaderServiceImpl();
        this.loanAmountValidationService = new LoanAmountValidationServiceImpl();
        this.lenderService = new LenderServiceImpl();
        this.loanCalculatorService = new LoanCalculatorServiceImpl();
    }

    public void execute() {

        BigDecimal requestedAmount = loanAmountValidationService.convertAndValidateRequestedAmount(requestedAmountStr);

        List<Lender> lenders = lendersCsvReaderService.loadLendersDataFromFile(lendersDataFilePath);

        List<Lender> lendersWithLowestRates = lenderService.findLendersWithLowestRates(lenders, requestedAmount);

        Quote quote = new Quote(requestedAmount, lendersWithLowestRates);

        quote.setMonthlyRepayment(loanCalculatorService.calculateMonthlyPayment(quote));

        quote.setTotalRepayment(loanCalculatorService.calculateTotalRepayment(quote));

        quote.setRate(loanCalculatorService.calculateTotalRate(quote));

        System.out.println(quote.toString());



    }



}
