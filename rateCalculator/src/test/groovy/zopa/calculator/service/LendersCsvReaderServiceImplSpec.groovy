package zopa.calculator.service

import spock.lang.Specification
import spock.lang.Unroll
import zopa.calculator.domain.Lender
import zopa.calculator.error.LendersDataFileError
import zopa.calculator.exception.LendersDataFileException
import zopa.calculator.service.impl.LendersCsvReaderServiceImpl

class LendersCsvReaderServiceImplSpec extends Specification {

    private static final String EXAMPLE_LENDERS_FILE = "/LenderData.csv"
    private static final String EXAMPLE_LENDERS_FILE_CORRUPTED = "/LenderDataCorrupted.csv"
    private static final String EXAMPLE_LENDERS_FILE_INVALID = "/LenderDataWithInvalidData.csv"
    private static final String NON_EXISTING_FILE_PATH = "/this/file/doesnt/exist.csv"

    def csvReaderService

    def setup() {
        csvReaderService = new LendersCsvReaderServiceImpl()
    }

    def "Csv reader file service must read the file successfully"() {
        given: "a csv file that contains lenders data"
        String locationOfFile = getClass().getResource(EXAMPLE_LENDERS_FILE).path

        when: "the contents of the file is read by the csv reading service"
        List<Lender> lenders = csvReaderService.loadLendersDataFromFile(locationOfFile)

        then: "the contents of the file is encapsulated in the list"
        lenders.size() == 7
        lenders.get(0).name == "Bob"
        lenders.get(0).rate == new BigDecimal("0.075")
        lenders.get(0).amount == new BigDecimal("640.0")
    }

    def "Csv reader file service must throw and exception when the file does not exist" () {
        given: "a file that doesn't exist"
        String locationOfFile = NON_EXISTING_FILE_PATH

        when: "the file reading service is called with this file"
        csvReaderService.loadLendersDataFromFile(locationOfFile)

        then: "an exception is thrown"
        def exception = thrown LendersDataFileException

        and: "with an appropriate error message"
        exception.getMessage() == LendersDataFileError.FILE_NOT_FOUND.getErrorMessage()
    }

    @Unroll("Csv reader file service must throw and exception when the file #scenario")
    def "Csv reader file service must validate the contain of the file" () {
        given: "a csv file that contains invalid data"
        String locationOfFile = getClass().getResource(filePath).path

        when: "the file reading service is called with this file"
        csvReaderService.loadLendersDataFromFile(locationOfFile)

        then: "an exception is thrown"
        def exception = thrown LendersDataFileException

        and: "with an appropriate error message"
        exception.getMessage() == errorMessage

        where:
        scenario               | filePath                       |errorMessage
        'contains invalid data'| EXAMPLE_LENDERS_FILE_INVALID   |LendersDataFileError.NUMBER_FORMAT.getErrorMessage()
        'is corrupted'         | EXAMPLE_LENDERS_FILE_CORRUPTED |LendersDataFileError.CORRUPTED_FILE.getErrorMessage()

    }
}