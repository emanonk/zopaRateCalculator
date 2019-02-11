
import org.junit.Before;
import org.junit.Test;
import zopa.calculator.facade.api.LoanQuoteService;



public class ACTest {

    LoanQuoteService loanQuoteService;

    @Before
    public void init(){
        //loanQuoteService=new loanQuoteServiceImpl();
    }

    @Test
    public void test1() {
        //given: Lenders zopa.calculator.domain.Data and loanAmount
//        LendersData lendersData = new LendersData();
//        BigDecimal loanAmount = new BigDecimal("1000");

        //when: a borrower requests a loan quote
//        String quoteDescription = loanQuoteService.getQuote(lendersData, loanAmount);


        //then: the quote must return "Requested amount", "Rate", "Monthly repayment", "Total repayment"
//        quoteDescription = "Requested amount: £1000\nRate: 7.0%\nMonthly repayment: £38.78\nTotal repayment: £1108.10";

    }

    @Test
    public void test2() {
        //given: Lenders zopa.calculator.domain.Data and loanAmount
//        LendersData lendersData = new LendersData();
//        BigDecimal loanAmount = new BigDecimal("1000");
//
//        //when: a borrower requests a loan quote
//        String quoteDescription = loanQuoteService.getQuote(lendersData, loanAmount);
//
//
//        //then: the quote must return "Requested amount", "Rate", "Monthly repayment", "Total repayment"
//        quoteDescription.equals("It is not possible to provide a quote at this time.") ;

    }
}
