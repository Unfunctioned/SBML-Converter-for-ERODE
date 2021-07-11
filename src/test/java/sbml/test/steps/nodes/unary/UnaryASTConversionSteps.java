package sbml.test.steps.nodes.unary;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.*;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.junit.Assert;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.unary.UnaryASTConverter;
import sbml.test.framework.TestDataManager;
import sbml.test.framework.nodes.unary.UnaryManager;

public class UnaryASTConversionSteps {
    private UnaryManager unaryManager;

    @Given("a UnaryManager has been initialized")
    public void aUnaryManagerHasBeenInitialized() {
        TestDataManager.setInstance(TestDataManager.Type.UNARY);
        unaryManager = (UnaryManager) TestDataManager.getInstance();
    }

    @Given("a unary ASTNode with type {string}")
    public void aUnaryASTNodeWithType(String arg0) {
        ASTNode node = new ASTNode();
        node.addChild(new ASTNode(1));
        node.setType(arg0);
        unaryManager.setNode(node);
    }

    @Given("an update function representing the NOT-operation")
    public void anUpdateFunctionRepresentingTheNOTOperation() {
        IUpdateFunction updateFunction = new NotBooleanUpdateFunction(new TrueUpdateFunction());
        unaryManager.setUpdateFunction(updateFunction);
    }

    @Given("an update function representing a NOT EQUALS AST")
    public void anUpdateFunctionRepresentingANOTEQUALSAST() {
        IUpdateFunction first = new TrueUpdateFunction();
        IUpdateFunction second = new FalseUpdateFunction();
        IUpdateFunction equals = new BooleanUpdateFunctionExpr(first, second, BooleanConnector.EQ);
        IUpdateFunction updateFunction = new NotBooleanUpdateFunction(equals);
        unaryManager.setUpdateFunction(updateFunction);
    }

    @When("the UnaryASTConverter is created for the ERODE conversion")
    public void theUnaryASTConverterIsCreatedForTheERODEConversion() {
        try {
            ASTNode node = unaryManager.getNode();
            unaryManager.setUnaryASTConverter(UnaryASTConverter.create(node));
        } catch (Exception e) {
            unaryManager.setException(e);
        }
    }

    @When("the UnaryASTConverter is created for the SBML conversion")
    public void theUnaryASTConverterIsCreatedForTheSBMLConversion() {
        try {
            IUpdateFunction updateFunction = unaryManager.getUpdateFunction();
            UnaryASTConverter converter = (UnaryASTConverter) UnaryASTConverter.create(updateFunction);
            unaryManager.setUnaryASTConverter(converter);
        } catch (Exception e) {
            unaryManager.setException(e);
        }
    }

    @Then("the NOT-operation has been converted to ERODE format")
    public void theNOTOperationHasBeenConvertedToERODEFormat() {
        Assert.assertNotNull(unaryManager.getUnaryASTConverter());
        IUpdateFunction updateFunction = unaryManager.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        Assert.assertEquals(NotBooleanUpdateFunction.class, updateFunction.getClass());
    }

    @Then("the NOT-operation has been converted to SBML format")
    public void theNOTOperationHasBeenConvertedToSBMLFormat() {
        Assert.assertNotNull(unaryManager.getUnaryASTConverter());
        ASTNode node = unaryManager.getNode();
        Assert.assertNotNull(node);
        Assert.assertEquals(ASTNode.Type.LOGICAL_NOT,node.getType());
    }

    @Then("the NOT-EQUALS-operation has been converted to SBML format")
    public void theNOTEQUALSOperationHasBeenConvertedToSBMLFormat() {
        Assert.assertNotNull(unaryManager.getUnaryASTConverter());
        ASTNode node = unaryManager.getNode();
        Assert.assertNotNull(node);
        Assert.assertEquals(ASTNode.Type.RELATIONAL_NEQ,node.getType());
    }
}
