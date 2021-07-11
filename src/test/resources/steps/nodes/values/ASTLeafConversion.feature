Feature: Converting leaves of an AST

  Background:
    Given a ValueManager has been initialized

  #Variable reference conversions
  Scenario: Converting a variable reference to ERODE format
    Given an ASTNode representing the variable "TestSpecies"
    When the ValueASTConverter is created for the ERODE conversion
    Then the ValueASTConverter creation succeeds
    Then the converted object is available in ERODE format

  Scenario: Converting a variable reference to SBML format
    Given an update function representing the variable "TestSpecies"
    When the ValueASTConverter is created for the SBML conversion
    Then the ValueASTConverter creation succeeds
    Then the converted object is available in SBML format

  #Integer conversion

  Scenario: Converting an integer constant to ERODE format
    Given an ASTNode representing the value 0
    When the ValueASTConverter is created for the ERODE conversion
    Then the ValueASTConverter creation succeeds
    Then the converted object is available in ERODE format
    Then the ERODE object represents the boolean value 0

  Scenario: Converting an integer constant to ERODE format
    Given an ASTNode representing the value 1
    When the ValueASTConverter is created for the ERODE conversion
    Then the ValueASTConverter creation succeeds
    Then the converted object is available in ERODE format
    Then the ERODE object represents the boolean value 1
    
  Scenario: Attempting to convert a non-boolean integer constant to ERODE format
    Given an ASTNode representing the value 2
    When the ValueASTConverter is created for the ERODE conversion
    Then an exception with message is "Given node value is not a boolean value" thrown

  #Boolean conversion

  Scenario: Converting the boolean constant FALSE to ERODE format
    Given an ASTNode representing the boolean constant "false"
    When the ValueASTConverter is created for the ERODE conversion
    Then the ValueASTConverter creation succeeds
    Then the converted object is available in ERODE format
    Then the ERODE object represents the boolean value 0

  Scenario: Converting the boolean constant TRUE to ERODE format
    Given an ASTNode representing the boolean constant "true"
    When the ValueASTConverter is created for the ERODE conversion
    Then the ValueASTConverter creation succeeds
    Then the converted object is available in ERODE format
    Then the ERODE object represents the boolean value 1

  Scenario: Converting the boolean constant FALSE to SBML format
    Given an update function representing the constant FALSE
    When the ValueASTConverter is created for the SBML conversion
    Then the ValueASTConverter creation succeeds
    Then the converted object is available in SBML format
    Then the SBML object represents the integer 0

  Scenario: Converting the boolean constant TRUE to SBML format
    Given an update function representing the constant TRUE
    When the ValueASTConverter is created for the SBML conversion
    Then the ValueASTConverter creation succeeds
    Then the converted object is available in SBML format
    Then the SBML object represents the integer 1

Scenario:
  Scenario: Attempting to convert an unknown constant to ERODE format
    Given an ASTNode of unknown format
    When the ValueASTConverter is created for the ERODE conversion
    Then an exception with message is "Unknown type name" thrown