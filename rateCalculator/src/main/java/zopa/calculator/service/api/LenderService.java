package zopa.calculator.service.api;

import zopa.calculator.domain.Lender;
import zopa.calculator.exception.ApplicationException;

import java.math.BigDecimal;
import java.util.List;

public interface LenderService {

    List<Lender> findLendersWithLowestRates(List<Lender> lenders, BigDecimal requestedAmount) throws ApplicationException;

}
