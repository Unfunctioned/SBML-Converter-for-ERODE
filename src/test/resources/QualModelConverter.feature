Feature: Converting the QualModelPlugin extension of an SBMLModel using the QualModelConverter

  Background:
    Given the ExceptionCollector is empty

  Scenario: Initialising a QualModel successfully
    Given a valid QualModelPlugin
    When attempting to create an QualModel instance
    Then the QualModel creation succeeds

  Scenario: Attempting to create a QualModel instance without an SBML-qual model
    Given that there is no QualModelPlugin instance
    When attempting to create an QualModel instance
    Then an exception with message is "Argument for @NotNull parameter 'qualModel' of sbml/conversion/QualModelConverter.<init> must not be null" thrown