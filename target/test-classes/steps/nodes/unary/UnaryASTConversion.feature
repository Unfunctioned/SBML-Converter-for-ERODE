Feature: Converting unary boolean expressions to ERODE format

  Background:
    Given a UnaryManager has been initialized

  #Not operator conversion
  Scenario: Converting the NOT-Operation to ERODE format
    Given a unary ASTNode with type "not"
    When the UnaryASTConverter is created for the ERODE conversion
    Then the NOT-operation has been converted to ERODE format

  Scenario: Converting the NOT-operation to SBML format
    Given an update function representing the NOT-operation
    When the UnaryASTConverter is created for the SBML conversion
    Then the NOT-operation has been converted to SBML format


  #SBML optimisations
  Scenario: Converting a negated EQUALS to a NOT-EQUALS operation in SBML
    Given an update function representing a NOT EQUALS AST
    When the UnaryASTConverter is created for the SBML conversion
    Then the NOT-EQUALS-operation has been converted to SBML format

  #Failure scenarios
  Scenario: Attempting to translate an unspecified unary operation to ERODE format
    Given a unary ASTNode with type "UNKNOWN"
    When the UnaryASTConverter is created for the ERODE conversion
    Then an exception with message is "Invalid type name" thrown