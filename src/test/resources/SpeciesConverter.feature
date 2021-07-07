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

  Scenario: Attempting to convert a list of multi-valued species into boolean ERODE species
    Given a valid list of qualitative species with initial values ranging from 0 to 2
    When attempting to create a SpeciesConverter instance
    Then an exception with message is "The value of the given species is outside the Boolean Domain" thrown