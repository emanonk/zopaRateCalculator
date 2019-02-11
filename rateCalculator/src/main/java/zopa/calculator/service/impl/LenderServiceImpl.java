package zopa.calculator.service.impl;

import zopa.calculator.domain.Lender;
import zopa.calculator.error.RequestedAmountError;
import zopa.calculator.exception.RequestedAmountException;
import zopa.calculator.service.api.LenderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LenderServiceImpl implements LenderService {

    public LenderServiceImpl() {
    }

    @Override
    public List<Lender> findLendersWithLowestRates(List<Lender> lenders, BigDecimal requestedAmount) {

        Collections.sort(lenders);

        BigDecimal remainingAmount = new BigDecimal(requestedAmount.toString());

        List<Lender> requiredLenders = new ArrayList<>();

        for(Lender lender:lenders) {
            if(remainingAmount.compareTo(lender.getAmount()) <= 0) {
                requiredLenders.add(new Lender(lender.getName(), new BigDecimal(lender.getRate().toString()), new BigDecimal(remainingAmount.toString())));
                remainingAmount = BigDecimal.ZERO;
                break;
            } else {
                remainingAmount = remainingAmount.subtract(lender.getAmount());
                requiredLenders.add(new Lender(lender.getName(), new BigDecimal(lender.getRate().toString()), new BigDecimal(lender.getAmount().toString())));
            }
        }
        if(remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
            throw new RequestedAmountException(RequestedAmountError.NOT_ENOUGH_CAPITAL.getErrorMessage());
        }



        return requiredLenders;
    }
}
