import com.kitfox.svg.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.BinaryASTConverter;
import sbml.conversion.nodes.NodeConverter;
import sbml.conversion.nodes.UnaryASTConverter;
import sbml.conversion.nodes.ValueASTConverter;
import umontreal.iro.lecuyer.simprocs.Bin;

public class NodeConverterSteps {
    private static final ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
    private ASTNode node;
    private NodeConverter nodeConverter;

    @Given("a boolean binary ASTNode")
    public void aBooleanBinaryASTNode() {
        node = new ASTNode();
        node.addChild(new ASTNode(1));
        node.addChild(new ASTNode(1));
        node.setType(ASTNode.Type.LOGICAL_AND);
    }

    @Given("a boolean unary ASTNode")
    public void aBooleanUnaryASTNode() {
        node = new ASTNode();
        node.addChild(new ASTNode(1));
        node.setType(ASTNode.Type.LOGICAL_NOT);
    }

    @Given("a boolean value ASTNode")
    public void aBooleanValueASTNode() {
        node = new ASTNode(1);
    }

    @Given("an invalid ASTNode")
    public void anInvalidASTNode() {
        node = new ASTNode();
        node.addChild(new ASTNode());
        node.addChild(new ASTNode());
        node.addChild(new ASTNode());
    }

    @When("the ASTConverter is created")
    public void theASTConverterIsCreated() {
        try {
            nodeConverter = NodeConverter.create(node);
        } catch (Exception e) {
            exceptionCollector.setException(e);
        }
    }

    @Then("a BinaryASTConverter is created succesfully")
    public void aBinaryASTConverterIsCreatedSuccesfully() {
        Assert.assertNotNull(nodeConverter);
        Assert.assertEquals(BinaryASTConverter.class, nodeConverter.getClass());
        Assert.assertNotNull(nodeConverter.getUpdateFunction());
    }

    @Then("a UnaryASTConverter is created successfully")
    public void aUnaryASTConverterIsCreatedSuccessfully() {
        Assert.assertNotNull(nodeConverter);
        Assert.assertEquals(UnaryASTConverter.class, nodeConverter.getClass());
        Assert.assertNotNull(nodeConverter.getUpdateFunction());
    }

    @Then("a ValueASTConverter is created successfully")
    public void aValueASTConverterIsCreatedSuccessfully() {
        Assert.assertNotNull(nodeConverter);
        Assert.assertEquals(ValueASTConverter.class, nodeConverter.getClass());
        Assert.assertNotNull(nodeConverter.getUpdateFunction());
    }
}
