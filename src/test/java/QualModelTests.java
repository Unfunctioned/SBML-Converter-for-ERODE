import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.models.QualModel;

public class QualModelTests {
    private QualModelPlugin qualModelPlugin;
    private QualModel qualModel;


    @Given("a valid QualModelPlugin")
    public void aValidQualModelPlugin() {
        qualModelPlugin = new QualModelPlugin(new Model());
    }

    @Given("that there is no QualModelPlugin instance")
    public void thatThereIsNoQualModelPluginInstance() {
        qualModel = null;
    }

    @When("attempting to create an QualModel instance")
    public void attemptingToCreateAnQualModelInstance() {
        try {
            qualModel = new QualModel(qualModelPlugin);
        } catch (Exception e) {
            ExceptionCollector.setExceptionInstance(e);
        }
    }

    @Then("the QualModel creation succeeds")
    public void theQualModelCreationSucceeds() {
        Assert.assertNull(ExceptionCollector.getExceptionInstance());
        Assert.assertNotNull(qualModel);
        Assert.assertEquals(qualModelPlugin,qualModel.getSbmlQualModel());

    }
}
