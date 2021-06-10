import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.models.SBMLModel;

public class SBMLModelTests {
    private Model model;
    private SBMLModel sbmlModel;

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
            sbmlModel = new SBMLModel(model);
        } catch (Exception e) {
            ExceptionCollector.setExceptionInstance(e);
        }
    }

    @Then("the SBMLModel creation succeeds")
    public void theSBMLModelCreationSucceeds() {
        Assert.assertNull(ExceptionCollector.getExceptionInstance());
        Assert.assertNotNull(sbmlModel);
        Assert.assertNotNull(sbmlModel.getName());
        Assert.assertNotNull(sbmlModel.getQualModel());
    }
}
