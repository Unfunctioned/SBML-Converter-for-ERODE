Feature: Converting SBML-transitions into ERODE's UpdateFunctions using the TransitionConverter

  Background:
    Given the ExceptionCollector is empty

  Scenario: Initialising an TransitionConverter instance successfully
    Given a valid list of transitions
    When attempting to create a TransitionConverter instance
    Then the TransitionConverter creation succeeds

  Scenario: Attempting to create a TransitionConverter without a list of transitions
    Given that there is no list of transitions
    When attempting to create a TransitionConverter instance
    Then an exception with message is "Argument for @NotNull parameter 'transitions' of sbml/conversion/transitions/TransitionConverter.create must not be null" thrown

  Scenario: Attempting to create a TransitionConverter instance using an empty list
    Given an empty list of transitions
    When attempting to create a TransitionConverter instance
    Then the TransitionConverter creation succeeds

  Scenario: Attempting to retrieve a functionTerm from the list of FunctionTerms using an invalid result level
    Given a valid list of transitions with result levels from 0 to 2 except level 1
    When attempting to create a TransitionConverter instance
    Then an exception with message is "No function term with result level 1 found" thrown