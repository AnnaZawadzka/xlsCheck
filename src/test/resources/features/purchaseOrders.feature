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