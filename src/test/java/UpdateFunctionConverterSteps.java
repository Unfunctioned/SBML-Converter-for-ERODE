import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import org.sbml.jsbml.ext.qual.Transition;
import sbml.conversion.SBMLConverter;
import sbml.conversion.UpdateFunctionConverter;

public class UpdateFunctionConverterSteps {
        private SBMLDocument sbmlDocument;

        private ListOf<Transition> transitions;
        private UpdateFunctionConverter updateFunctionConverter;

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

    @When("attempting to create a UpdateFunctionConverter instance")
    public void attemptingToCreateAUpdateFunctionConverterInstance() {
        try {
            updateFunctionConverter = new UpdateFunctionConverter(transitions);
        } catch (Exception e) {
            ExceptionCollector.setExceptionInstance(e);
        }
    }

    @Then("the UpdateFunctionConverter creation succeeds")
    public void theUpdateFunctionConverterCreationSucceeds() {
        Assert.assertNotNull(updateFunctionConverter);
        Assert.assertNotNull(updateFunctionConverter.getErodeUpdateFunctions());
    }
}
