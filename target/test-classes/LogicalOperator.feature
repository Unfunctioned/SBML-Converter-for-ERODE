Feature: Translating logical operators to ERODE format

  Background:
    Given the ExceptionCollector is empty
    Given an Operator-object instance

  Scenario: Creating an AND-operation in ERODE format
    Given an update function term X in ERODE format
    Given an update function term Y in ERODE format
    When the AND-operation is created
    Then a boolean update function representing the AND-operation is created successfully

  Scenario: Creating an OR-operation in ERODE format
    Given an update function term X in ERODE format
    Given an update function term Y in ERODE format
    When the OR-operation is created
    Then a boolean update function representing the OR-operation is created successfully

  Scenario: Creating a NOT-operation in ERODE format
    Given an update function term X in ERODE format
    When the NOT-operation is created
    Then a boolean update function representing the NOT-operation is created successfully

  Scenario: Creating a XOR-operation in ERODE format
    Given an update function term X in ERODE format
    Given an update function term Y in ERODE format
    When the XOR-operation is created
    Then a boolean update function representing the XOR-operation is created successfully