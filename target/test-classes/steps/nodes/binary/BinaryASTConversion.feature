Feature: Converting binary boolean expressions to ERODE format

  Background:
    Given a BinaryManager has been initialized

  #SBML to ERODE conversions
  Scenario: Converting the AND-Operation to ERODE format
    Given a binary ASTNode with type "and"
    When the BinaryASTConverter is created for the ERODE conversion
    Then an ERODE update function representing an AND operation was created

  Scenario: Converting the OR-Operation to ERODE format
    Given a binary ASTNode with type "or"
    When the BinaryASTConverter is created for the ERODE conversion
    Then an ERODE update function representing an OR operation was created

  Scenario: Converting the XOR-Operation to ERODE format
    Given a binary ASTNode with type "xor"
    When the BinaryASTConverter is created for the ERODE conversion
    Then an ERODE update function representing an XOR operation was created

  Scenario: Converting the IMPLIES-Operation to ERODE format
    Given a binary ASTNode with type "implies"
    When the BinaryASTConverter is created for the ERODE conversion
    Then an ERODE update function representing an IMPLIES operation was created

  Scenario: Converting the EQ-Operation to ERODE format
    Given a binary ASTNode with type "eq"
    When the BinaryASTConverter is created for the ERODE conversion
    Then an ERODE update function representing an EQ operation was created

  Scenario: Converting the NEQ-Operation to ERODE format
    Given a binary ASTNode with type "neq"
    When the BinaryASTConverter is created for the ERODE conversion
    Then an ERODE update function representing an NEQ operation was created

  #ERODE to SBML conversions
  Scenario: Converting the AND-operation to SBML format
    Given an update function representing the "and"-operation
    When the BinaryASTConverter is created for the SBML conversion
    Then the converted "and"-operation is available in SBML format

  Scenario: Converting the OR-operation to SBML format
    Given an update function representing the "or"-operation
    When the BinaryASTConverter is created for the SBML conversion
    Then the converted "or"-operation is available in SBML format

  Scenario: Converting the XOR-operation to SBML format
    Given an update function representing the "xor"-operation
    When the BinaryASTConverter is created for the SBML conversion
    Then the converted "xor"-operation is available in SBML format

  Scenario: Converting the IMPLIES-operation to SBML format
    Given an update function representing the "implies"-operation
    When the BinaryASTConverter is created for the SBML conversion
    Then the converted "implies"-operation is available in SBML format

  Scenario: Converting the EQ-operation to SBML format
    Given an update function representing the "eq"-operation
    When the BinaryASTConverter is created for the SBML conversion
    Then the converted "eq"-operation is available in SBML format

  Scenario: Converting the EQ-operation to SBML format
    Given an update function representing the "neq"-operation
    When the BinaryASTConverter is created for the SBML conversion
    Then the converted "neq"-operation is available in SBML format

  #Failure scenarios
  Scenario: Attempting to translate an unspecified operation to ERODE format
    Given a binary ASTNode with type "UNKNOWN"
    When the BinaryASTConverter is created for the ERODE conversion
    Then an exception with message is "Invalid type name" thrown
    