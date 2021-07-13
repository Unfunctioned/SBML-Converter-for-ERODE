Feature: Converting the top-level SBMLDocument class to ERODE format

  Background:
    Given a DocumentManager has been initialised

  #ERODE conversion
  Scenario: Successfully converting an SBMLDocument to ERODE format
    Given an SBMLDocument instance with the SBML-qual extension
    When attempting to create a SBMLDocumentConverter instance for ERODE format
    Then the SBMLDocumentConverter creation succeeds
    Then the complete ERODE model structure is available through the SBMLConverter

  #SBML conversion
  Scenario: Successfully converting a boolean network to SBML format
    Given a boolean network in SBML format to convert
    When attempting to create a SBMLDocumentConverter instance for SBML format
    Then the SBMLDocumentConverter creation succeeds
    Then an SBMLDocument representing the boolean network is available through the SBMLDocument converter

  #Failure Scenarios
  Scenario: Failing to initialise an SBMLConverter instance given an non-SBMLQual model
    Given an SBMLDocument instance without the SBML-qual extension
    When attempting to create a SBMLDocumentConverter instance for ERODE format
    Then an exception with message is "Invalid input, the SBML-model is not an SBML-qual model" thrown

  #Converting an SBML file
  Scenario: Adding ERODE-Species to a boolean network in ERODE format
    Given a boolean network in SBML format
    When attempting to create a SBMLDocumentConverter instance for ERODE format
    Then the SBMLDocumentConverter creation succeeds
    Then the complete ERODE model structure is available through the SBMLConverter
    Then the boolean network contains a List of Species