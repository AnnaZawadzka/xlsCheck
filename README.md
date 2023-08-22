# XLS Checker

A Java project to verify the content and structure of `purchase_orders.xls` using Cucumber and Apache POI.

## Feature

Verification of `purchase_orders.xls` content.

```gherkin
Feature: Verification of purchase_orders.xls content

  Scenario: Verify the content and structure of purchase_orders.xls
    Given File with name "purchase_orders.xls" exists
    When I check if file contains at least one sheet
    Then The headers are:
      | Buyer |
      | Buyer |
      | Buyer External ID |
      | Sales Order |
    And The file should contain more than 10 lines
```

## Setup

### Maven Dependencies

The project uses Maven as its build tool and has dependencies on:
- Cucumber for behavior-driven development.
- Apache POI for handling Excel files.
- Log4j2 for logging.
- AssertJ for assertions.

Refer to the `pom.xml` for detailed configuration.

### Code Structure

The core of the project is the `StepDefinitions` class located in the `ch.zawadzka.xlschecker` package. This class provides the Cucumber step definitions and uses Apache POI to interact with the Excel file.

## How to Use

1. Ensure that Maven is installed and set up correctly on your machine.
2. Navigate to the project root directory.
3. Run `mvn clean install` to build the project.
4. To execute tests, run `mvn test`.
