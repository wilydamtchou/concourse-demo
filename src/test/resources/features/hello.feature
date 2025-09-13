Feature: Hello endpoint

  Scenario: GET / should return Hello World
    When I send a GET request to "/"
    Then the response should be "Hello World"
