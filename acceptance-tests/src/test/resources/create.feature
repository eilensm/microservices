Feature: a rating can be created
  Scenario: client creates a rating and receives a rating object with an id
    Given the rating service is available
    When the client creates a rating with value 4711
    Then the client receives status code of 201
    And the client receives a rating object with a non empty id