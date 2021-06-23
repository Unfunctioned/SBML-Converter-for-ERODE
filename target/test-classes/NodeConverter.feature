Feature: Creating the correct converters matching given boolean expressions

  Background:
    Given the ExceptionCollector is empty
    
  
  Scenario: Creating a BinaryASTConverter from a boolean binary expression
    Given a boolean binary ASTNode
    When the ASTConverter is created
    Then a BinaryASTConverter is created succesfully

  Scenario: Creating a UnaryASTConverter from a boolean unary expression
    Given a boolean unary ASTNode
    When the ASTConverter is created
    Then a UnaryASTConverter is created successfully

  Scenario: Creating a ValueASTConverter from a boolean value expression
    Given a boolean value ASTNode
    When the ASTConverter is created
    Then a ValueASTConverter is created successfully

  Scenario: Attempting to create a converter for an invalid boolean expression
    Given an invalid ASTNode
    When the ASTConverter is created
    Then an exception with message is "A node cannot have more than 2 children" thrown