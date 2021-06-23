import com.kitfox.svg.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.ValueASTConverter;

public class ValueASTConverterSteps {

    private static final ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();

    private ASTNode node;
    private ValueASTConverter valueASTConverter;

    @Given("an ASTNode with representing the variable {string}")
    public void anASTNodeWithRepresentingTheVariable(String arg0) {
        node = new ASTNode(arg0);
    }

    @Given("an ASTNode representing the value {int}")
    public void anASTNodeRepresentingTheValue(int arg0) {
        node = new ASTNode(arg0);
    }

    @Given("an ASTNode representing the boolean constant {string}")
    public void anASTNodeRepresentingTheBooleanConstant(String arg0) {
        node = new ASTNode();
        node.setType(arg0);
    }

    @Given("an ASTNode of unknown format")
    public void anASTNodeOfUnknownFormat() {
        node = new ASTNode();
        node.setType("UNKNOWN");
    }

    @When("the ValueASTConverter is created")
    public void theValueASTConverterIsCreated() {
        try {
            valueASTConverter = new ValueASTConverter(node);
        } catch (Exception e) {
            exceptionCollector.setException(e);
        }
    }

    @Then("the ValueASTConverter creation succeeds")
    public void theValueASTConverterCreationSucceeds() {
        Assert.assertNull(exceptionCollector.getException());
        Assert.assertNotNull(valueASTConverter);
    }

    @Then("the converted update function in ERODE format is accessible")
    public void theConvertedUpdateFunctionInERODEFormatIsAccessible() {
        Assert.assertNotNull(valueASTConverter.getUpdateFunction());
    }
}
