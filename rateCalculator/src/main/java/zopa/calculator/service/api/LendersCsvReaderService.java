package zopa.calculator.service.api;

import zopa.calculator.domain.Lender;
import java.util.List;

public interface LendersCsvReaderService {

    List<Lender> loadLendersDataFromFile(String filePath);
}
