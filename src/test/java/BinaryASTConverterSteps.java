import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.mk_latn.No;
import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.junit.Assert;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.BinaryASTConverter;

public class BinaryASTConverterSteps {
    private static final ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();

    private ASTNode node;
    private BinaryASTConverter binaryASTConverter;

    @Given("a binary ASTNode with type {string}")
    public void aBinaryASTNodeWithType(String arg0) {
        node = new ASTNode();
        node.addChild(new ASTNode(1));
        node.addChild(new ASTNode(1));
        node.setType(arg0);
    }

    @When("the binary ASTNode is converted")
    public void theBinaryASTNodeIsConverted() {
        try {
            binaryASTConverter = new BinaryASTConverter(node);
        } catch (Exception e) {
            exceptionCollector.setException(e);
        }
    }

    @Then("an ERODE update function representing an AND operation was created")
    public void anERODEUpdateFunctionRepresentingAnANDOperationWasCreated() {
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());

        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(BooleanConnector.AND,andExpr.getOperator());
    }

    @Then("an ERODE update function representing an OR operation was created")
    public void anERODEUpdateFunctionRepresentingAnOROperationWasCreated() {
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());

        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(BooleanConnector.OR,andExpr.getOperator());
    }

    @Then("an ERODE update function representing an XOR operation was created")
    public void anERODEUpdateFunctionRepresentingAnXOROperationWasCreated() {
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());
        //Outer expression
        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(BooleanConnector.AND,andExpr.getOperator());

        //Left inner expression
        IUpdateFunction orFunction = andExpr.getFirst();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, orFunction.getClass());
        BooleanUpdateFunctionExpr orExpr = (BooleanUpdateFunctionExpr)orFunction;
        Assert.assertEquals(BooleanConnector.OR, orExpr.getOperator());

        //Right inner expression
        IUpdateFunction notFunction = andExpr.getSecond();
        Assert.assertEquals(NotBooleanUpdateFunction.class, notFunction.getClass());
        NotBooleanUpdateFunction notExpr = (NotBooleanUpdateFunction) notFunction;
        IUpdateFunction andFunction = notExpr.getInnerUpdateFunction();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, andFunction.getClass());
        andExpr = (BooleanUpdateFunctionExpr) andFunction;
        Assert.assertEquals(BooleanConnector.AND, andExpr.getOperator());
    }

    @Then("an ERODE update function representing an EQ operation was created")
    public void anERODEUpdateFunctionRepresentingAnEQOperationWasCreated() {
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());
        //Outer expression
        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(BooleanConnector.AND,andExpr.getOperator());

        //Left inner expression
        IUpdateFunction implyFunction = andExpr.getFirst();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, implyFunction.getClass());
        BooleanUpdateFunctionExpr implyExpr = (BooleanUpdateFunctionExpr) implyFunction;
        Assert.assertEquals(BooleanConnector.IMPLIES, implyExpr.getOperator());

        //Right inner expression
        implyFunction = andExpr.getSecond();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, implyFunction.getClass());
        implyExpr = (BooleanUpdateFunctionExpr) implyFunction;
        Assert.assertEquals(BooleanConnector.IMPLIES, implyExpr.getOperator());
    }

    @Then("an ERODE update function representing an NEQ operation was created")
    public void anERODEUpdateFunctionRepresentingAnNEQOperationWasCreated() {
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(NotBooleanUpdateFunction.class, updateFunction.getClass());
        //Outer not expression
        NotBooleanUpdateFunction notExpr = (NotBooleanUpdateFunction)updateFunction;
        //Equals expression
        IUpdateFunction equalsFunction = notExpr.getInnerUpdateFunction();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, equalsFunction.getClass());
        //Outer expression
        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr)equalsFunction;
        Assert.assertEquals(BooleanConnector.AND,andExpr.getOperator());

        //Left inner expression
        IUpdateFunction implyFunction = andExpr.getFirst();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, implyFunction.getClass());
        BooleanUpdateFunctionExpr implyExpr = (BooleanUpdateFunctionExpr) implyFunction;
        Assert.assertEquals(BooleanConnector.IMPLIES, implyExpr.getOperator());

        //Right inner expression
        implyFunction = andExpr.getSecond();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, implyFunction.getClass());
        implyExpr = (BooleanUpdateFunctionExpr) implyFunction;
        Assert.assertEquals(BooleanConnector.IMPLIES, implyExpr.getOperator());
    }

    @Then("an ERODE update function representing an IMPLIES operation was created")
    public void anERODEUpdateFunctionRepresentingAnIMPLIESOperationWasCreated() {
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());

        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(BooleanConnector.IMPLIES,andExpr.getOperator());
    }
}
