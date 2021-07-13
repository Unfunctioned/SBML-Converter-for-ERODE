package sbml.test.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import sbml.test.framework.ExceptionCollector;
import sbml.test.framework.Expression;
import sbml.test.framework.TestDataManager;

public class CommonSteps {

    public static String GetPath() {
        return "./src/test/resources/CorticalAreaDevelopment.sbml";
    }

    @Then("an exception with message is {string} thrown")
    public void anExceptionWithMessageIsThrown(String arg0) {
        TestDataManager dataManager = TestDataManager.getInstance();
        Exception exception = dataManager.getException();
        Assert.assertNotNull(exception);
        Assert.assertEquals(arg0, exception.getMessage());
    }
}
