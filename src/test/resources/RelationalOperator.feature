Feature: Translating logical operators to ERODE format

  Background:
    Given the ExceptionCollector is empty
    Given the ExpressionCollector is empty

  Scenario: Creating an Equals-operation in ERODE format
    Given an update function term X in ERODE format
    Given an update function term Y in ERODE format
    When the Equals-operation is created
    Then a boolean update function representing the Equals-operation is created successfully

  Scenario: Creating an NotEquals-operation in ERODE format
    Given an update function term X in ERODE format
    Given an update function term Y in ERODE format
    When the NotEquals-operation is created
    Then a boolean update function representing the NotEquals-operation is created successfully