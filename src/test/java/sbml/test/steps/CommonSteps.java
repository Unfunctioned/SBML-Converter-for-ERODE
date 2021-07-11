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


    @Given("the ExceptionCollector is empty")
    public void theExceptionCollectorIsEmpty() {
        TestDataManager dataManager = TestDataManager.getInstance();
        dataManager.setException(null);
        Assert.assertNull(dataManager.getException());
    }

    @Then("an exception with message is {string} thrown")
    public void anExceptionWithMessageIsThrown(String arg0) {
        TestDataManager dataManager = TestDataManager.getInstance();
        Exception exception = dataManager.getException();
        Assert.assertNotNull(exception);
        Assert.assertEquals(arg0, exception.getMessage());
    }

    @Given("the ExpressionCollector is empty")
    public void theExpressionCollectorIsEmpty() {
        Expression.clear();
    }
}
