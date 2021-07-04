import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.QualModelConverter;

public class QualModelTests {
    private QualModelPlugin qualModelPlugin;
    private QualModelConverter qualModelConverter;


    @Given("a valid QualModelPlugin")
    public void aValidQualModelPlugin() {
        qualModelPlugin = new QualModelPlugin(new Model());
    }

    @Given("that there is no QualModelPlugin instance")
    public void thatThereIsNoQualModelPluginInstance() {
        qualModelConverter = null;
    }

    @When("attempting to create an QualModel instance")
    public void attemptingToCreateAnQualModelInstance() {
        try {
            qualModelConverter = new QualModelConverter(qualModelPlugin);
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @Then("the QualModel creation succeeds")
    public void theQualModelCreationSucceeds() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());
        Assert.assertNotNull(qualModelConverter);
        Assert.assertEquals(qualModelPlugin, qualModelConverter.getSbmlQualModel());

    }
}
