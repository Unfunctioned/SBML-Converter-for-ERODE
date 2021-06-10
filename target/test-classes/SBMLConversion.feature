Feature: Converting the top-level SBMLDocument class to ERODE format

  Background:
    Given the ExceptionCollector is empty

  #SBMLConverter construction and initialisation
  Scenario: Initializing an SBMLConverter instance successfully
    Given an SBMLDocument instance with the SBML-qual extension
    When attempting to create an SBMLConverter instance
    Then the SBMLConverter creation succeeds

  Scenario: Failing to initialise an SBMLConverter instance given an non-SBMLQual model
    Given an SBMLDocument instance without the SBML-qual extension
    When attempting to create an SBMLConverter instance
    Then an exception with message is "Invalid input, the SBML-model is not an SBML-qual model" thrown

  #Top-level conversion
  Scenario: Converting an SBML-qual model successfully
    Given an SBMLConverter instance created from an SBML-qual model
    When the model is converted
    Then the ERODE data structures have been created successfully