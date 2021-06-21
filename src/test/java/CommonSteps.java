import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.junit.Before;

public class CommonSteps {

    public static String GetPath() {
        return "./src/test/resources/CorticalAreaDevelopment.sbml";
    }

    @Given("the ExceptionCollector is empty")
    public void theExceptionCollectorIsEmpty() {
        ExceptionCollector.clear();
        Assert.assertNull(ExceptionCollector.getExceptionInstance());
    }

    @Then("an exception with message is {string} thrown")
    public void anExceptionWithMessageIsThrown(String arg0) {
        Assert.assertNotNull(ExceptionCollector.getExceptionInstance());
        Assert.assertEquals(arg0, ExceptionCollector.getExceptionInstance().getMessage());
    }
}
