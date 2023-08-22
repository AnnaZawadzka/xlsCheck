package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import static java.lang.String.*;
import static org.assertj.core.api.Assertions.*;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class StepDefinitions {
    private InputStream inputStream;
    private Sheet sheet;

    @Given("^File with name \"([^\"]*)\" exists$")
    public void file_with_name_exists(String filename) {
        inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        assertThat(inputStream)
                .as(format("%s does not exist in test resources.", filename))
                .isNotNull();
    }

    @When("^I check the header of the file$")
    public void i_check_the_header_of_the_file() throws Exception {
        sheet = new HSSFWorkbook(inputStream).getSheetAt(0);
        assertThat(sheet)
                .as("The Excel file does not contain any sheets.")
                .isNotNull();
    }

    @Then("^The headers are:$")
    public void the_header_should_be(List<String> expectedHeaders) {
        Row headerRow = sheet.getRow(0);

        assertThat(headerRow.getPhysicalNumberOfCells())
                .as("Number of headers doesn't match. Expected: %d", expectedHeaders.size())
                .isEqualTo(expectedHeaders.size());

        IntStream.range(0, expectedHeaders.size()).forEach(i -> {
            String actualValue = Optional.ofNullable(headerRow.getCell(i))
                    .map(cell -> cell.getStringCellValue().trim())
                    .orElse("null");

            assertThat(actualValue)
                    .as("Header mismatch at column %d. Expected: %s", i + 1, expectedHeaders.get(i))
                    .isEqualTo(expectedHeaders.get(i));
        });
    }


    @Then("^The file should contain more than (\\d+) lines$")
    public void the_file_should_contain_more_than_lines(int numberOfLines) {
        assertThat(sheet.getPhysicalNumberOfRows())
                .as("The file contains %d lines, which is not more than %d",
                        sheet.getPhysicalNumberOfRows(), numberOfLines)
                .isGreaterThan(numberOfLines);
    }
}

