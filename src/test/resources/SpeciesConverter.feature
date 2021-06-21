Feature: Converting the SBML-Species into an ERODE-Species

  Background:
    Given the ExceptionCollector is empty

  Scenario: Initialising a SpeciesConverter instance successfully
    Given a valid list of qualitative species
    When attempting to create a SpeciesConverter instance
    Then the SpeciesConverter creation succeeds

  Scenario: Attempting to create a SpeciesConverter without a list of qualitative species
    Given that there is no list of qualitative species
    When attempting to create a SpeciesConverter instance
    Then an exception with message is "Argument for @NotNull parameter 'listOfQualitativeSpecies' of sbml/conversion/SpeciesConverter.<init> must not be null" thrown

  Scenario: Attempting to create a SpeciesConverter instance using an empty list
    Given an empty list of qualitative species
    When attempting to create a SpeciesConverter instance
    Then the SpeciesConverter creation succeeds

  Scenario: Converting various SBML-Species into ERODE species with different initial values
    Given a valid list of qualitative species with initial values ranging from 0 to 2
    When attempting to create a SpeciesConverter instance
    Then the SpeciesConverter creation succeeds
    * the SpeciesConverter contains a list of ERODE-Species with initial values ranging from 0 to 2