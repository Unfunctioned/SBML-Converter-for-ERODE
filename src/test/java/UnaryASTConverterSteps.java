import com.kitfox.svg.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.junit.Assert;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.BinaryASTConverter;
import sbml.conversion.nodes.UnaryASTConverter;

public class UnaryASTConverterSteps {
    private static final ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();

    private ASTNode node;
    private UnaryASTConverter unaryASTConverter;

    @Given("a unary ASTNode with type {string}")
    public void aUnaryASTNodeWithType(String arg0) {
        node = new ASTNode();
        node.addChild(new ASTNode(1));
        node.setType(arg0);
    }

    @When("the unary ASTNode is converted")
    public void theUnaryASTNodeIsConverted() {
        try {
            unaryASTConverter = new UnaryASTConverter(node);
        } catch (Exception e) {
            exceptionCollector.setException(e);
        }
    }

    @Then("an ERODE update function representing an NOT operation was created")
    public void anERODEUpdateFunctionRepresentingAnNOTOperationWasCreated() {
        Assert.assertNotNull(unaryASTConverter);
        IUpdateFunction updateFunction = unaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(NotBooleanUpdateFunction.class, updateFunction.getClass());
    }
}
