Feature: Translation of Constants and Variables into ERODE format

  Background:
    Given the ExceptionCollector is empty

  Scenario: Translating a variable reference into ERODE format
    Given a species with name "TestSpecies" to be referenced
    When the Reference-operation is created
    Then an update function representing the variable "TestSpecies" is created successfully

  Scenario: Translating the (binary) FALSE-constant into ERODE format
    Given an ASTNode with value 0 representing a constant
    When the constant is created
    Then an update function representing the FALSE constant is created

  Scenario: Translating the (binary) TRUE-constant into ERODE format
    Given an ASTNode with value 1 representing a constant
    When the constant is created
    Then an update function representing the TRUE constant is created

  Scenario: Attempting to translate a non boolean-constant into ERODE format
    Given an ASTNode with value 2 representing a constant
    When the constant is created
    Then an exception with message is "Given node value is not a boolean value" thrown

  Scenario: Translating the (verbose) boolean FALSE-constant into ERODE format
    Given an ASTNode of type "false" representing a boolean constant
    When the boolean value is created
    Then an update function representing the FALSE constant is created

  Scenario: Translating the (verbose) boolean TRUE-constant into ERODE format
    Given an ASTNode of type "true" representing a boolean constant
    When the boolean value is created
    Then an update function representing the TRUE constant is created

  Scenario: Attempting to translate a non boolean constant into a boolean constant in ERODE format
    Given an ASTNode of type "UNKNOWN" representing a boolean constant
    When the boolean value is created
    Then an exception with message is "Given node is not a boolean constant" thrown
