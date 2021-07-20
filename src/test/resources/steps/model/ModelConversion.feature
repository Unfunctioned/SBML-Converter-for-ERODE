Feature: Converting the SBML model contained in an SBMLDocument using the ModelConverter

  Background:
    Given a new ModelManager instance


  #ERODE conversion
  Scenario: Successfully converting an SBML model to ERODE format
    Given a valid SBML-qual model
    When attempting to create a ModelConverter instance for ERODE format
    Then the ModelConverter creation succeeds
    Then a list of species converted to ERODE format is available through the ModelConverter
    Then a map of update functions converted to ERODE format is available through the ModelConverter

  Scenario: Successfully converting an ERODE model to SBML format
    Given there is a valid boolean network available to the model
    When attempting to create a ModelConverter instance for SBML format
    Then the ModelConverter creation succeeds
    Then a Model representing the boolean network is available through the ModelConverter

  #Failure scenarios
  Scenario: Attempting to create an SBMLModel instance without an SBML-qual model
    Given that there is no SBML Model
    When attempting to create a ModelConverter instance for ERODE format
    Then an exception with message is "Argument for @NotNull parameter 'model' of sbml/conversion/model/ModelManager.create must not be null" thrown
    
  Scenario: Attempting to create an SBMLModel instance using a non-SBML-qual model
    Given a non-SBML-qual model
    When attempting to create a ModelConverter instance for ERODE format
    Then an exception with message is "Invalid input, the SBML-model is not an SBML-qual model" thrown