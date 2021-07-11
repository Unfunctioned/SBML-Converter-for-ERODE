Feature: Converting SBML-transitions into ERODE's UpdateFunctions using the TransitionConverter

  Background:
    Given a TransitionManager has been initialized

  #ERODE conversion
  Scenario: Initialising a TransitionConverter instance successfully
    Given a valid list of transitions
    When the TransitionConverter is created for the ERODE conversion
    Then the TransitionConverter creation succeeds

  Scenario: Attempting to create a TransitionConverter instance using an empty list
    Given an empty list of transitions
    When the TransitionConverter is created for the ERODE conversion
    Then the TransitionConverter creation succeeds
    Then a Map ERODE update functions is available

  #SBML conversion
  Scenario: Initialising a TransitionConverter instance successfully
    Given a valid Map of UpdateFunctions
    When the TransitionConverter is created for the SBML conversion
    Then the TransitionConverter creation succeeds
    Then a List of SBML transitions is available

  Scenario: Converting multiple variable reference from ERODE format
    Given a valid Map of UpdateFunctions with multiple variable references
    When the TransitionConverter is created for the SBML conversion
    Then  the TransitionConverter creation succeeds
    Then  a List of SBML transitions is available

  #Detailed
  Scenario: Converting a transition from SBML to ERODE
    Given an empty list of transitions
    Given the inputs "input1" and "input2" in a list of inputs
    Given a the output "output1" in a list of Output species
    Given a list of function terms that reference "input1" and "input2"
    Given a transition created from the inputs, outputs and function terms
    Given that the transition was added to the list of transitions
    When the TransitionConverter is created for the ERODE conversion
    Then the TransitionConverter creation succeeds
    Then a Map ERODE update functions is available
    Then the Map contains the key "output1"
    Then the update function mapped to "output1" references the inputs "input1" and "input2"

  Scenario: Converting an update function from ERODE to SBML
    Given an empty map of update functions
    Given an update function referencing "input1" and "input2"
    Given a key "output1" mapping to the update function in the map
    When the TransitionConverter is created for the SBML conversion
    Then the TransitionConverter creation succeeds
    Then the list of transitions contains 1 transition
    Then the transition contains a list of 2 inputs
    Then the inputs reference "input1" and "input2"
    Then the transition contains a list of 1 output
    Then the output references "output1"
    Then the transition contains a list of 2 function terms
    Then one of the function terms is the default term




  Scenario: Converting an update function from ERODE to SBML

  #Failure scenario
  Scenario: Attempting to create a TransitionConverter without a list of transitions
    Given that there is no list of transitions
    When the TransitionConverter is created for the ERODE conversion
    Then an exception with message is "Argument for @NotNull parameter 'transitions' of sbml/conversion/transitions/TransitionConverter.create must not be null" thrown

  Scenario: Attempting to retrieve a functionTerm from the list of FunctionTerms using an invalid result level
    Given a valid list of transitions with result levels from 0 to 2 except level 1
    When the TransitionConverter is created for the ERODE conversion
    Then an exception with message is "No function term with result level 1 found" thrown