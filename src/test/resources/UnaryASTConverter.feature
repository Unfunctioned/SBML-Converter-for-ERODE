Feature: Converting unary boolean expressions to ERODE format

  Background:
    Given the ExceptionCollector is empty

  Scenario: Translating the NOT-Operation to ERODE format
    Given a unary ASTNode with type "not"
    When the unary ASTNode is converted
    Then an ERODE update function representing an NOT operation was created

  Scenario: ttempting to translate an unspecified unary operation to ERODE format
    Given a unary ASTNode with type "UNKNOWN"
    When the unary ASTNode is converted
    Then an exception with message is "Invalid type name" thrown