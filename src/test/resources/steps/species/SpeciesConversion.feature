Feature: Converting the SBML-Species into an ERODE-Species

  Background:
    Given the SpeciesManger has been initialised

  #ERODE conversion
  Scenario: Converting species to ERODE format successfully
    Given a valid list of qualitative species
    When attempting to create a SpeciesConverter instance for ERODE format
    Then the SpeciesConverter creation succeeds

  Scenario: Attempting to create a SpeciesConverter instance using an empty list
    Given an empty list of qualitative species
    When attempting to create a SpeciesConverter instance for ERODE format
    Then the SpeciesConverter creation succeeds
    Then a List of ERODE species is available

  #SBML Conversions
  Scenario: Converting species to SBML format successfully
    Given a list of ERODE species
    When attempting to create a SpeciesConverter instance for SBML format
    Then the SpeciesConverter creation succeeds
    Then a list of qualitative species is available

  #Failure scenarios
  Scenario: Attempting to create a SpeciesConverter without a list of qualitative species
    Given that there is no list of qualitative species
    When attempting to create a SpeciesConverter instance for ERODE format
    Then an exception with message is "Argument for @NotNull parameter 'qualitativeSpecies' of sbml/conversion/species/SpeciesManager.create must not be null" thrown

  Scenario: Attempting to convert a list of multi-valued species into boolean ERODE species
    Given a valid list of qualitative species with initial values ranging from 0 to 2
    When attempting to create a SpeciesConverter instance for ERODE format
    Then an exception with message is "The value of the given species is outside the Boolean Domain" thrown