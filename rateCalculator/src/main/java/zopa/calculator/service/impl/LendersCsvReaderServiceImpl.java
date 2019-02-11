package zopa.calculator.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import zopa.calculator.domain.Lender;
import zopa.calculator.error.LendersDataFileError;
import zopa.calculator.exception.LendersDataFileException;
import zopa.calculator.service.api.LendersCsvReaderService;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LendersCsvReaderServiceImpl implements LendersCsvReaderService {


    public LendersCsvReaderServiceImpl() {
    }

    @Override
    public List<Lender> loadLendersDataFromFile(String filePath) {

        List<Lender> lenders = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            csvParser.forEach(record -> lenders.add(new Lender(
                    record.get(0), new BigDecimal(record.get(1)), new BigDecimal(record.get(2)))));
        } catch (IOException ioe) {
            throw new LendersDataFileException(LendersDataFileError.FILE_NOT_FOUND.getErrorMessage());
        } catch (NumberFormatException nfe) {
            throw new LendersDataFileException(LendersDataFileError.NUMBER_FORMAT.getErrorMessage());
        } catch (ArrayIndexOutOfBoundsException ofb ){
            throw new LendersDataFileException(LendersDataFileError.CORRUPTED_FILE.getErrorMessage());
        }
        return lenders;
    }
}
