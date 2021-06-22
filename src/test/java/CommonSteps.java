import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CommonSteps {

    public static String GetPath() {
        return "./src/test/resources/CorticalAreaDevelopment.sbml";
    }


    @Given("the ExceptionCollector is empty")
    public void theExceptionCollectorIsEmpty() {
        ExceptionCollector.clear();
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());
    }

    @Then("an exception with message is {string} thrown")
    public void anExceptionWithMessageIsThrown(String arg0) {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Exception exception = exceptionCollector.getException();
        Assert.assertNotNull(exception);
        Assert.assertEquals(arg0, exception.getMessage());
    }

    @Given("the ExpressionCollector is empty")
    public void theExpressionCollectorIsEmpty() {
        Expression.clear();
    }
}
