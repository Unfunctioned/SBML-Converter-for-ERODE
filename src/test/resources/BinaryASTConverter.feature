Feature: Converting binary boolean expressions to ERODE format

  Background:
    Given the ExceptionCollector is empty

  Scenario: Translating the AND-Operation to ERODE format
    Given a binary ASTNode with type "and"
    When the binary ASTNode is converted
    Then an ERODE update function representing an AND operation was created

  Scenario: Translating the OR-Operation to ERODE format
    Given a binary ASTNode with type "or"
    When the binary ASTNode is converted
    Then an ERODE update function representing an OR operation was created

  Scenario: Translating the XOR-Operation to ERODE format
    Given a binary ASTNode with type "xor"
    When the binary ASTNode is converted
    Then an ERODE update function representing an XOR operation was created

  Scenario: Translating the IMPLIES-Operation to ERODE format
    Given a binary ASTNode with type "implies"
    When the binary ASTNode is converted
    Then an ERODE update function representing an IMPLIES operation was created

  Scenario: Translating the EQ-Operation to ERODE format
    Given a binary ASTNode with type "eq"
    When the binary ASTNode is converted
    Then an ERODE update function representing an EQ operation was created

  Scenario: Translating the NEG-Operation to ERODE format
    Given a binary ASTNode with type "neq"
    When the binary ASTNode is converted
    Then an ERODE update function representing an NEQ operation was created

  Scenario: Attempting to translate an unspecified operation to ERODE format
    Given a binary ASTNode with type "UNKNOWN"
    When the binary ASTNode is converted
    Then an exception with message is "Invalid type name" thrown
    