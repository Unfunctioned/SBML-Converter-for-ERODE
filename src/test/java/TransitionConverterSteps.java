import io.cucumber.gherkin.Func;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import org.sbml.jsbml.ext.qual.Transition;
import sbml.conversion.SBMLConverter;
import sbml.conversion.TransitionConverter;

public class TransitionConverterSteps {
        private static final ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        private SBMLDocument sbmlDocument;

        private ListOf<Transition> transitions;
        private TransitionConverter transitionConverter;
        private ListOf<FunctionTerm> functionTerms;

    @Given("a valid list of transitions")
    public void aValidListOfTransitions() {
        String path = CommonSteps.GetPath();
        try {
            sbmlDocument = (SBMLDocument) SBMLConverter.read(path);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Model model = sbmlDocument.getModel();
        QualModelPlugin qualModelPlugin = (QualModelPlugin) model.getExtension("qual");
        transitions = qualModelPlugin.getListOfTransitions();
        Assert.assertNotNull(transitions);
    }

    @Given("that there is no list of transitions")
    public void thatThereIsNoListOfTransitions() {
        transitions = null;
    }

    @Given("an empty list of transitions")
    public void anEmptyListOfTransitions() {
        transitions = new ListOf<>();
    }

    @Given("a valid list of transitions with result levels from {int} to {int} except level {int}")
    public void aValidListOfTransitionsWithResultLevelsFromToExceptLevel(int arg0, int arg1, int arg2) {
        transitions = new ListOf<>();
        for(int i = arg0; i<=arg1; i++) {
            if(i != arg2) {
                Transition t = new Transition();
                FunctionTerm f = new FunctionTerm();
                f.setResultLevel(i);
                t.addFunctionTerm(f);
                transitions.add(t);
            }
        }
    }

    @When("attempting to create a TransitionConverter instance")
    public void attemptingToCreateAUpdateFunctionConverterInstance() {
        try {
            transitionConverter = new TransitionConverter(transitions);
        } catch (Exception e) {
            exceptionCollector.setException(e);
        }
    }

    @Then("the TransitionConverter creation succeeds")
    public void theUpdateFunctionConverterCreationSucceeds() {
        Assert.assertNotNull(transitionConverter);
        Assert.assertNotNull(transitionConverter.getErodeUpdateFunctions());
    }
}
