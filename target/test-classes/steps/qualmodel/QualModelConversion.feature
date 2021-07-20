Feature: Converting the QualModelPlugin extension of an SBMLModel using the QualModelConverter

  Background:
    Given a QualModelManager has been initialized

  #ERODE Conversion
  Scenario: Successfully converting a QualModel to ERODE format
    Given a valid QualModelPlugin
    When the QualModelConverter is created for the ERODE conversion
    Then the QualModelConverter creation succeeds
    Then a list of species converted to ERODE format is available
    Then a map of update functions converted to ERODE format is available

  #SBML Conversion
  Scenario: Successfully converting a boolean network to SBML format
    Given a valid boolean network
    Given an SBML model to be populated with data
    When the QualModelConverter is created for the SBML conversion
    Then the QualModelConverter creation succeeds
    Then a QualModel plugin representing the boolean network is available

  #Failure scenarios
  Scenario: Attempting to create a QualModel instance without an SBML-qual model
    Given that there is no QualModelPlugin instance
    When the QualModelConverter is created for the ERODE conversion
    Then an exception with message is "Argument for @NotNull parameter 'qualModelPlugin' of sbml/conversion/qualmodel/QualModelManager.create must not be null" thrown