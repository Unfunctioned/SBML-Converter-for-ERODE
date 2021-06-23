Feature: Converting values into ERODE format

  Background:
    Given the ExceptionCollector is empty

  Scenario: Converting a variable reference to ERODE format
    Given an ASTNode with representing the variable "TestSpecies"
    When the ValueASTConverter is created
    Then the ValueASTConverter creation succeeds
    Then the converted update function in ERODE format is accessible

  Scenario: Converting an integer constant to ERODE format
    Given an ASTNode representing the value 0
    When the ValueASTConverter is created
    Then the ValueASTConverter creation succeeds
    Then the converted update function in ERODE format is accessible

  Scenario: Converting the boolean constant FALSE to ERODE format
    Given an ASTNode representing the boolean constant "false"
    When the ValueASTConverter is created
    Then the ValueASTConverter creation succeeds
    Then the converted update function in ERODE format is accessible

  Scenario: Converting the boolean constant TRUE to ERODE format
    Given an ASTNode representing the boolean constant "true"
    When the ValueASTConverter is created
    Then the ValueASTConverter creation succeeds
    Then the converted update function in ERODE format is accessible

  Scenario: Attempting to convert an unknown constant to ERODE format
    Given an ASTNode of unknown format
    When the ValueASTConverter is created
    Then an exception with message is "Unknown type name" thrown