Feature: Converting the top-level SBMLDocument class to ERODE format
  #SBMLConverter construction and initialisation
  Scenario: Initializing an SBMLConverter instance successfully
    Given an SBMLDocument instance with the SBML-qual extension
    When attempting to create an SBMLConverter instance
    Then the creation succeeds

  Scenario: Failing to initialise an SBMLConverter instance given an non-SBMLQual model
    Given an SBMLDocument instance without the SBML-qual extension
    When attempting to create an SBMLConverter instance
    Then an exception with message is "Argument for @NotNull parameter 'qualModel' of sbml/models/QualModel.<init> must not be null" thrown

  #Top-level conversion
  Scenario: Converting an SBML-qual model successfully
    Given an SBMLConverter instance created from an SBML-qual model
    When the model is converted
    Then the ERODE data structures have been created successfully