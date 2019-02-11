import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class calcTest {

    public static double calculateMonthlyPayment(
            int loanAmount, int termInMonths, double interestRate) {

        // Convert interest rate into a decimal
        // eg. 6.5% = 0.065

        interestRate /= 100.0;

        // Monthly interest rate
        // is the yearly rate divided by 12

        double monthlyRate = interestRate / 12.0;

        // The length of the term in months
        // is the number of years times 12

        //int termInMonths = termInYears * 12;

        // Calculate the monthly payment
        // Typically this formula is provided so
        // we won't go into the details

        // The Math.pow() method is used calculate values raised to a power

        double monthlyPayment =
                (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -termInMonths));

        return monthlyPayment;
    }

    public static double cagr(double beginningValue, double endingValue, int numOfPeriods){

            double CAGR = Math.pow((endingValue / beginningValue), 1 / 36) - 1;
        System.out.printf(""+CAGR);
            return Math.round(CAGR * 10000) / 100;
    }

    @Test
    public void rateTesting(){
        BigDecimal pv = new BigDecimal("1000");
        BigDecimal fv = new BigDecimal("1108.04");

        double n = 36;

        //double = Math.pow(fv.divide(pv), 1/n) -1



    }


    @Test
    public void test() {

        // Scanner is a great class for getting
        // console input from the user



        // Prompt user for details of loan


        int loanAmount = 480;


        int termInYears = 3;


        double interestRate = 6.9;

        // Display details of loan

        //double monthlyPayment = calculateMonthlyPayment(loanAmount, termInYears, interestRate);

        NumberFormat currencyFormat =
                //NumberFormat.getCurrencyInstance();
                NumberFormat.getCurrencyInstance(Locale.UK);

        NumberFormat interestFormat =
                NumberFormat.getPercentInstance();

        double monthlyPayment = calculateMonthlyPayment(480, 36, 6.9);
        System.out.println("Monthly Payment: "+ currencyFormat.format(monthlyPayment));
        double monthlyPayment1 = calculateMonthlyPayment(60, 36, 7.1);
        System.out.println("Monthly Payment: "+ currencyFormat.format(monthlyPayment1));
        double monthlyPayment2 = calculateMonthlyPayment(460, 36, 7.1);
        System.out.println("Monthly Payment: "+ currencyFormat.format(monthlyPayment2));


        double total = monthlyPayment + monthlyPayment1 + monthlyPayment2;

        System.out.println("total:"+total);


        double cagr = cagr(1000, total, 36);

        System.out.println(cagr);

        // NumberFormat is useful for formatting numbers
        // In our case we'll use it for
        // formatting currency and percentage values



        // Display details of the loan

//        System.out.println("Loan Amount: "+
//                currencyFormat.format(loanAmount));
//        System.out.println("Loan Term: "+
//                termInYears+" years");
//        System.out.println("Interest Rate: "+
//                interestFormat.format(interestRate));
//        System.out.println("Monthly Payment: "+ currencyFormat.format(monthlyPayment));

    }
}
