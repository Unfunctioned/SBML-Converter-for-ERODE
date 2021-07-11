package sbml.test.steps.nodes.binary;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.TrueUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.junit.Assert;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.binary.BinaryASTConverter;
import sbml.test.framework.TestDataManager;
import sbml.test.framework.nodes.binary.BinaryManager;

import static sbml.test.framework.TestDataManager.Type;

public class BinaryASTConversion {

    private BinaryManager binaryManager;

    @Given("a BinaryManager has been initialized")
    public void aBinaryManagerHasBeenInitialized() {
        TestDataManager.setInstance(Type.BINARY);
        binaryManager = (BinaryManager) TestDataManager.getInstance();
    }

    @Given("a binary ASTNode with type {string}")
    public void aBinaryASTNodeWithType(String arg0) {
        ASTNode node = new ASTNode();
        node.addChild(new ASTNode(1));
        node.addChild(new ASTNode(1));
        node.setType(arg0);
        binaryManager.setNode(node);
    }

    @Given("an update function representing the {string}-operation")
    public void anUpdateFunctionRepresentingTheOperation(String arg0) {
        IUpdateFunction first = new TrueUpdateFunction();
        IUpdateFunction second = new TrueUpdateFunction();
        IUpdateFunction updateFunction;
        switch (arg0) {
            case "and":
                updateFunction = new BooleanUpdateFunctionExpr(first, second, BooleanConnector.AND);
                binaryManager.setUpdateFunction(updateFunction);
                break;
            case "or":
                updateFunction = new BooleanUpdateFunctionExpr(first, second, BooleanConnector.OR);
                binaryManager.setUpdateFunction(updateFunction);
                break;
            case "xor":
                updateFunction = new BooleanUpdateFunctionExpr(first, second, BooleanConnector.XOR);
                binaryManager.setUpdateFunction(updateFunction);
                break;
            case "implies":
                updateFunction = new BooleanUpdateFunctionExpr(first, second, BooleanConnector.IMPLIES);
                binaryManager.setUpdateFunction(updateFunction);
                break;
            case "eq":
                updateFunction = new BooleanUpdateFunctionExpr(first, second, BooleanConnector.EQ);
                binaryManager.setUpdateFunction(updateFunction);
                break;
            case "neq":
                updateFunction = new BooleanUpdateFunctionExpr(first, second, BooleanConnector.NEQ);
                binaryManager.setUpdateFunction(updateFunction);
                break;
            default:
                Assert.fail("Invalid operator");
        }
    }

    @When("the BinaryASTConverter is created for the ERODE conversion")
    public void theBinaryASTConverterIsCreatedForTheERODEConversion() {
        try {
            BinaryASTConverter binaryASTConverter = BinaryASTConverter.create(binaryManager.getNode());
            binaryManager.setBinaryASTConverter(binaryASTConverter);
        } catch (Exception e) {
            binaryManager.setException(e);
        }
    }

    @When("the BinaryASTConverter is created for the SBML conversion")
    public void theBinaryASTConverterIsCreatedForTheSBMLConversion() {
        try {
            BooleanUpdateFunctionExpr expression = (BooleanUpdateFunctionExpr) binaryManager.getUpdateFunction();
            BinaryASTConverter binaryASTConverter = BinaryASTConverter.create(expression);
            binaryManager.setBinaryASTConverter(binaryASTConverter);
        } catch (Exception e) {
            binaryManager.setException(e);
        }
    }

    @Then("an ERODE update function representing an AND operation was created")
    public void anERODEUpdateFunctionRepresentingAnANDOperationWasCreated() {
        BinaryASTConverter binaryASTConverter = binaryManager.getBinaryASTConverter();
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());

        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(BooleanConnector.AND,andExpr.getOperator());
    }

    @Then("an ERODE update function representing an OR operation was created")
    public void anERODEUpdateFunctionRepresentingAnOROperationWasCreated() {
        BinaryASTConverter binaryASTConverter = binaryManager.getBinaryASTConverter();
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());

        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(BooleanConnector.OR,andExpr.getOperator());
    }

    @Then("an ERODE update function representing an XOR operation was created")
    public void anERODEUpdateFunctionRepresentingAnXOROperationWasCreated() {
        BinaryASTConverter binaryASTConverter = binaryManager.getBinaryASTConverter();
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());
        //Outer expression
        BooleanUpdateFunctionExpr xorExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(BooleanConnector.XOR,xorExpr.getOperator());
    }

    @Then("an ERODE update function representing an EQ operation was created")
    public void anERODEUpdateFunctionRepresentingAnEQOperationWasCreated() {
        BinaryASTConverter binaryASTConverter = binaryManager.getBinaryASTConverter();
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());
        BooleanUpdateFunctionExpr eqExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(BooleanConnector.EQ,eqExpr.getOperator());
    }

    @Then("an ERODE update function representing an NEQ operation was created")
    public void anERODEUpdateFunctionRepresentingAnNEQOperationWasCreated() {
        BinaryASTConverter binaryASTConverter = binaryManager.getBinaryASTConverter();
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());
        //Outer not expression
        BooleanUpdateFunctionExpr neqExpr = (BooleanUpdateFunctionExpr) updateFunction;
        //Equals expression
        Assert.assertEquals(neqExpr.getOperator(), BooleanConnector.NEQ);
    }

    @Then("an ERODE update function representing an IMPLIES operation was created")
    public void anERODEUpdateFunctionRepresentingAnIMPLIESOperationWasCreated() {
        BinaryASTConverter binaryASTConverter = binaryManager.getBinaryASTConverter();
        Assert.assertNotNull(binaryASTConverter);
        IUpdateFunction updateFunction = binaryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());

        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(BooleanConnector.IMPLIES,andExpr.getOperator());
    }

    @Then("the converted {string}-operation is available in SBML format")
    public void theConvertedOperationIsAvailableInSBMLFormat(String arg0) {
        ASTNode node = binaryManager.getNode();
        Assert.assertNotNull(node);
        switch (arg0) {
            case "and":
                Assert.assertEquals(ASTNode.Type.LOGICAL_AND, node.getType());
                break;
            case "or":
                Assert.assertEquals(ASTNode.Type.LOGICAL_OR, node.getType());
                break;
            case "xor":
                Assert.assertEquals(ASTNode.Type.LOGICAL_XOR, node.getType());
                break;
            case "implies":
                Assert.assertEquals(ASTNode.Type.LOGICAL_IMPLIES, node.getType());
                break;
            case "eq":
                Assert.assertEquals(ASTNode.Type.RELATIONAL_EQ, node.getType());
                break;
            case "neq":
                Assert.assertEquals(ASTNode.Type.RELATIONAL_NEQ, node.getType());
                break;
            default:
                Assert.fail("Invalid operator");
        }
    }
}
