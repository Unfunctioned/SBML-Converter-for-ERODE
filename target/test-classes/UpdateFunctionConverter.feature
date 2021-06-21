Feature: Converting SBML-transitions into ERODE's UpdateFunctions

  Background:
    Given the ExceptionCollector is empty

  Scenario: Initialising an UpdateFunctionConverter instance successfully
    Given a valid list of transitions
    When attempting to create a UpdateFunctionConverter instance
    Then the UpdateFunctionConverter creation succeeds

  Scenario: Attempting to create a UpdateFunctionConverter without a list of transitions
    Given that there is no list of transitions
    When attempting to create a UpdateFunctionConverter instance
    Then an exception with message is "Argument for @NotNull parameter 'listOfTransitions' of sbml/conversion/UpdateFunctionConverter.<init> must not be null" thrown

  Scenario: Attempting to create a UpdateFunctionConverter instance using an empty list
    Given an empty list of transitions
    When attempting to create a UpdateFunctionConverter instance
    Then the UpdateFunctionConverter creation succeeds