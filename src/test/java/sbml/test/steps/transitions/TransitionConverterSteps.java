package sbml.test.steps.transitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.*;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.junit.Assert;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.ext.qual.*;
import sbml.conversion.document.SBMLManager;
import sbml.conversion.transitions.ITransitionConverter;
import sbml.conversion.transitions.TransitionManager;
import sbml.test.framework.TestDataManager;
import sbml.test.framework.transitions.TransitionDataManager;
import sbml.test.steps.CommonSteps;

import java.util.LinkedHashMap;

import static sbml.test.framework.TestDataManager.Type;

public class TransitionConverterSteps {

    private TransitionDataManager transitionDataManager;

    @Given("a TransitionManager has been initialized")
    public void aTransitionManagerHasBeenInitialized() {
        TestDataManager.setInstance(Type.TRANSITION);
        transitionDataManager = (TransitionDataManager) TestDataManager.getInstance();
    }

    @Given("a valid list of transitions")
    public void aValidListOfTransitions() {
        String path = CommonSteps.GetPath();
        try {
            SBMLDocument sbmlDocument = (SBMLDocument) SBMLManager.read(path);
            transitionDataManager.setSbmlDocument(sbmlDocument);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        SBMLDocument sbmlDocument = transitionDataManager.getSbmlDocument();
        Model model = sbmlDocument.getModel();
        QualModelPlugin qualModelPlugin = (QualModelPlugin) model.getExtension("qual");
        ListOf<Transition> transitions = qualModelPlugin.getListOfTransitions();
        transitionDataManager.setTransitions(transitions);
        Assert.assertNotNull(transitions);
    }

    @Given("that there is no list of transitions")
    public void thatThereIsNoListOfTransitions() {
        transitionDataManager.setTransitions(null);
    }

    @Given("an empty list of transitions")
    public void anEmptyListOfTransitions() {
        transitionDataManager.setTransitions(new ListOf<>(3,1));
    }

    @Given("a valid list of transitions with result levels from {int} to {int} except level {int}")
    public void aValidListOfTransitionsWithResultLevelsFromToExceptLevel(int arg0, int arg1, int arg2) {
        ListOf<Transition> transitions = new ListOf<>(3,1);
        for(int i = arg0; i<=arg1; i++) {
            if(i != arg2) {
                Transition t = new Transition();
                FunctionTerm f = new FunctionTerm();
                f.setResultLevel(i);
                t.addFunctionTerm(f);
                transitions.add(t);
            }
        }
        transitionDataManager.setTransitions(transitions);
    }

    @Given("a valid Map of UpdateFunctions")
    public void aValidMapOfUpdateFunctions() {
        LinkedHashMap<String, IUpdateFunction> map = new LinkedHashMap<>();
        String outputSpecies = "TestSpecies0";
        IUpdateFunction updateFunction = new NotBooleanUpdateFunction(new TrueUpdateFunction());
        map.put(outputSpecies, updateFunction);
        transitionDataManager.setUpdateFunctions(map);
    }

    @Given("a valid Map of UpdateFunctions with multiple variable references")
    public void aValidMapOfUpdateFunctionsWithMultipleVariableReferences() {
        LinkedHashMap<String, IUpdateFunction> map = new LinkedHashMap<>();
        String outputSpecies = "TestSpecies0";
        ReferenceToNodeUpdateFunction var1 = new ReferenceToNodeUpdateFunction("input1");
        ReferenceToNodeUpdateFunction var2 = new ReferenceToNodeUpdateFunction("input2");
        IUpdateFunction updateFunction = new BooleanUpdateFunctionExpr(var1, var2, BooleanConnector.AND);
        map.put(outputSpecies, updateFunction);
        transitionDataManager.setUpdateFunctions(map);
    }

    @Given("the inputs {string} and {string} in a list of inputs")
    public void theInputsAndInAListOfInputs(String arg0, String arg1) {
        ListOf<Input> inputs = new ListOf<>(3,1);
        Input input1 = new Input(3,1);
        input1.setQualitativeSpecies(arg0);
        Input input2 = new Input(3,1);
        input2.setQualitativeSpecies(arg1);
        inputs.add(input1);
        inputs.add(input2);
        transitionDataManager.setInputs(inputs);
    }

    @Given("a the output {string} in a list of Output species")
    public void aTheOutputInAListOfOutputSpecies(String arg0) {
        ListOf<Output> outputs = new ListOf<>(3,1);
        Output output = new Output(3,1);
        output.setQualitativeSpecies(arg0);
        outputs.add(output);
        transitionDataManager.setOutputs(outputs);
    }

    @Given("a list of function terms that reference {string} and {string}")
    public void aListOfFunctionTermsThatReferenceAnd(String arg0, String arg1) {
        ListOf<FunctionTerm> functionTerms = new ListOf<>(3,1);
        FunctionTerm functionTerm = new FunctionTerm(3,1);
        functionTerm.setResultLevel(1);
        ASTNode ref1 = new ASTNode(arg0);
        ASTNode ref2 = new ASTNode(arg1);
        ASTNode and = new ASTNode(ASTNode.Type.LOGICAL_AND);
        and.addChild(ref1);
        and.addChild(ref2);
        functionTerm.setMath(and);
        functionTerms.add(functionTerm);
        transitionDataManager.setFunctionTerms(functionTerms);
    }

    @Given("a transition created from the inputs, outputs and function terms")
    public void aTransitionCreatedFromTheInputsOutputsAndFunctionTerms() {
        Transition transition = new Transition(3,1);
        transition.setListOfInputs(transitionDataManager.getInputs());
        transition.setListOfOutputs(transitionDataManager.getOutputs());
        transition.setListOfFunctionTerms(transitionDataManager.getFunctionTerms());
        transitionDataManager.setTransition(transition);
    }

    @Given("that the transition was added to the list of transitions")
    public void thatTheTransitionWasAddedToTheListOfTransitions() {
        ListOf<Transition> transitions = transitionDataManager.getTransitions();
        transitions.add(transitionDataManager.getTransition());
        transitionDataManager.setTransitions(transitions);
    }

    @Given("an empty map of update functions")
    public void anEmptyMapOfUpdateFunctions() {
        transitionDataManager.setUpdateFunctions(new LinkedHashMap<>());
    }

    @Given("an update function referencing {string} and {string}")
    public void anUpdateFunctionReferencingAnd(String arg0, String arg1) {
        ReferenceToNodeUpdateFunction first = new ReferenceToNodeUpdateFunction(arg0);
        ReferenceToNodeUpdateFunction second = new ReferenceToNodeUpdateFunction(arg1);
        BooleanUpdateFunctionExpr and = new BooleanUpdateFunctionExpr(first, second, BooleanConnector.AND);
        transitionDataManager.setUpdateFunction(and);
    }

    @Given("a key {string} mapping to the update function in the map")
    public void aKeyMappingToTheUpdateFunctionInTheMap(String arg0) {
        LinkedHashMap<String, IUpdateFunction> map = transitionDataManager.getUpdateFunctions();
        map.put(arg0, transitionDataManager.getUpdateFunction());
        transitionDataManager.setUpdateFunctions(map);
    }

    @When("the TransitionConverter is created for the ERODE conversion")
    public void theTransitionConverterIsCreatedForTheERODEConversion() {
        try {
            ListOf<Transition> transitions = transitionDataManager.getTransitions();
            ITransitionConverter transitionConverter = TransitionManager.create(transitions);
            transitionDataManager.setTransitionConverter(transitionConverter);
        } catch (Exception e) {
            transitionDataManager.setException(e);
        }
    }

    @When("the TransitionConverter is created for the SBML conversion")
    public void theTransitionConverterIsCreatedForTheSBMLConversion() {
        try {
            LinkedHashMap<String, IUpdateFunction> updateFunctions = transitionDataManager.getUpdateFunctions();
            ITransitionConverter transitionConverter = TransitionManager.create(updateFunctions);
            transitionDataManager.setTransitionConverter(transitionConverter);
        } catch (Exception e) {
            transitionDataManager.setException(e);
        }
    }

    @Then("the TransitionConverter creation succeeds")
    public void theUpdateFunctionConverterCreationSucceeds() {
        ITransitionConverter transitionConverter = transitionDataManager.getTransitionConverter();
        Assert.assertNotNull(transitionConverter);
    }

    @Then("a Map ERODE update functions is available")
    public void aMapERODEUpdateFunctionsIsAvailable() {
        Assert.assertNotNull(transitionDataManager.getUpdateFunctions());
    }

    @Then("a List of SBML transitions is available")
    public void aListOfSBMLTransitionsIsAvailable() {
        Assert.assertNotNull(transitionDataManager.getTransitions());
    }

    @Then("the Map contains the key {string}")
    public void theMapContainsTheKey(String arg0) {
        LinkedHashMap<String, IUpdateFunction> map = transitionDataManager.getUpdateFunctions();
        Assert.assertTrue(map.containsKey(arg0));
    }

    @Then("the update function mapped to {string} references the inputs {string} and {string}")
    public void theUpdateFunctionMappedToReferencesTheInputsAnd(String arg0, String arg1, String arg2) {
        LinkedHashMap<String, IUpdateFunction> map = transitionDataManager.getUpdateFunctions();
        IUpdateFunction updateFunction = map.get(arg0);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());
        BooleanUpdateFunctionExpr and = (BooleanUpdateFunctionExpr) updateFunction;
        ReferenceToNodeUpdateFunction first = (ReferenceToNodeUpdateFunction) and.getFirst();
        ReferenceToNodeUpdateFunction second = (ReferenceToNodeUpdateFunction) and.getSecond();
        Assert.assertEquals(arg1, first.toString());
        Assert.assertEquals(arg2, second.toString());
    }

    @Then("the list of transitions contains {int} transition")
    public void theListOfTransitionsContainsTransition(int arg0) {
        ListOf<Transition> transitions = transitionDataManager.getTransitions();
        Assert.assertEquals(arg0, transitions.size());
        transitionDataManager.setTransition(transitions.get(0));
    }

    @Then("the transition contains a list of {int} inputs")
    public void theTransitionContainsAListOfInputs(int arg0) {
        Transition transition = transitionDataManager.getTransition();
        ListOf<Input> inputs = transition.getListOfInputs();
        Assert.assertEquals(arg0, inputs.size());
        transitionDataManager.setInputs(inputs);
    }

    @Then("the inputs reference {string} and {string}")
    public void theInputsReferenceAnd(String arg0, String arg1) {
        ListOf<Input> inputs = transitionDataManager.getInputs();
        boolean firstContained = false;
        boolean secondContained = false;
        for (Input i : inputs) {
            String species = i.getQualitativeSpecies();
            if(species.equals(arg0))
                firstContained = true;
            else if(species.equals(arg1))
                secondContained = true;
        }
        Assert.assertTrue(firstContained && secondContained);
    }

    @Then("the transition contains a list of {int} output")
    public void theTransitionContainsAListOfOutput(int arg0) {
        Transition t = transitionDataManager.getTransition();
        ListOf<Output> outputs = t.getListOfOutputs();
        transitionDataManager.setOutputs(outputs);
        Assert.assertEquals(arg0, outputs.size());
    }

    @Then("the output references {string}")
    public void theOutputReferences(String arg0) {
        ListOf<Output> outputs = transitionDataManager.getOutputs();
        boolean isReferenced = false;
        for(Output o : outputs) {
            String species = o.getQualitativeSpecies();
            if(species.equals(arg0))
                isReferenced = true;
        }
        Assert.assertTrue(isReferenced);
    }

    @Then("the transition contains a list of {int} function terms")
    public void theTransitionContainsAListOfFunctionTerms(int arg0) {
        Transition t = transitionDataManager.getTransition();
        ListOf<FunctionTerm> functionTerms = t.getListOfFunctionTerms();
        transitionDataManager.setFunctionTerms(functionTerms);
        Assert.assertEquals(arg0, functionTerms.size());
    }

    @Then("one of the function terms is the default term")
    public void oneOfTheFunctionTermsIsTheDefaultTerm() {
        ListOf<FunctionTerm> functionTerms = transitionDataManager.getFunctionTerms();
        boolean isDefault = false;
        for (FunctionTerm f : functionTerms) {
            if(f.isDefaultTerm())
                isDefault = true;
        }
        Assert.assertTrue(isDefault);
    }
}
