Feature: Converting n-ary boolean expressions to ERODE format

  Background:
    Given a NaryManager has been initialized

  #SBML to ERODE conversions
  Scenario: Converting the AND-Operation to ERODE format
    Given a n-ary ASTNode with type "and"
    When the NaryASTConverter is created for the ERODE conversion
    Then an ERODE update function representing a N-ary AND operation was created

  Scenario: Converting the OR-Operation to ERODE format
    Given a n-ary ASTNode with type "or"
    When the NaryASTConverter is created for the ERODE conversion
    Then an ERODE update function representing a N-ary OR operation was created

  Scenario: Converting the XOR-Operation to ERODE format
    Given a n-ary ASTNode with type "xor"
    When the NaryASTConverter is created for the ERODE conversion
    Then an ERODE update function representing a N-ary XOR operation was created

  # Failure scenarios
  Scenario: Attempting to convert a node as N-ary with a single child
    Given an invalid n-ary node with type "and" and 1 child(ren)
    When the NaryASTConverter is created for the ERODE conversion
    Then an exception with message is "Nary operators require at least two children" thrown

  Scenario: Attempting to convert a non-N-ary operator as N-ary
    Given an invalid n-ary node with type "eq" and 2 child(ren)
    When the NaryASTConverter is created for the ERODE conversion
    Then an exception with message is "Invalid type name" thrown



