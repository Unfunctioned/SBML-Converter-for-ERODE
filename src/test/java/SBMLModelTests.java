import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.ModelConverter;

public class SBMLModelTests {
    private Model model;
    private ModelConverter modelConverter;

    @Given("a valid SBML-qual model")
    public void aValidSBMLQualModel() {
        model = new Model(3,1);
        QualModelPlugin qualModelPlugin = new QualModelPlugin(new Model());
        model.addExtension("qual", qualModelPlugin);
    }

    @Given("that there is no SBML Model")
    public void thatThereIsNoSBMLModel() {
        model = null;
    }

    @Given("a non-SBML-qual model")
    public void aNonSBMLQualModel() {
        model = new Model(3,1);
    }

    @When("attempting to create an SBMLModel instance")
    public void attemptingToCreateAnSBMLModelInstance() {
        try {
            modelConverter = new ModelConverter(model);
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @Then("the SBMLModel creation succeeds")
    public void theSBMLModelCreationSucceeds() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());
        Assert.assertNotNull(modelConverter);
        Assert.assertNotNull(modelConverter.getName());
        Assert.assertNotNull(modelConverter.getQualModel());
    }
}
