Feature: Converting the SBML model contained in an SBMLDocument

  Background:
    Given the ExceptionCollector is empty
  #Initialising SBMLModel instances
  Scenario: Initialising an SBMLModel instance successfully
    Given a valid SBML-qual model
    When attempting to create an SBMLModel instance
    Then the SBMLModel creation succeeds

  Scenario: Attempting to create an SBMLModel instance without an SBML-qual model
    Given that there is no SBML Model
    When attempting to create an SBMLModel instance
    Then an exception with message is "Argument for @NotNull parameter 'model' of sbml/models/SBMLModel.<init> must not be null" thrown
    
  Scenario: Attempting to create an SBMLModel instance using a non-SBML-qual model
    Given a non-SBML-qual model
    When attempting to create an SBMLModel instance
    Then an exception with message is "Invalid input, the SBML-model is not an SBML-qual model" thrown