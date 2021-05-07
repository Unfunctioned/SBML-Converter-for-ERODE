Feature: Reading an SBML file

  Scenario: Successfully reading a SBML file
    Given a valid path to a SBML file
    When the file is read by the module
    Then an AST model containing the file data will be available

  Scenario: