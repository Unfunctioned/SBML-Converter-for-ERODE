package sbml.test.steps.nodes.nary;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.FalseUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.TrueUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.junit.Assert;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.nary.NaryASTConverter;
import sbml.conversion.nodes.nary.NaryManager;
import sbml.test.framework.TestDataManager;
import sbml.test.framework.nodes.nary.NaryDataManager;

import java.util.Random;

public class NaryASTConversion {

    private NaryDataManager naryDataManager;

    @Given("a NaryManager has been initialized")
    public void aNaryManagerHasBeenInitialized() {
        TestDataManager.setInstance(TestDataManager.Type.NARY);
        naryDataManager = (NaryDataManager) TestDataManager.getInstance();
    }

    @Given("a n-ary ASTNode with type {string}")
    public void aNAryASTNodeWithType(String arg0) {
        Random rand = new Random();
        int val = rand.nextInt(5)+2;
        ASTNode node = new ASTNode();
        for(int i = 0; i<val; i++) {
            node.addChild(new ASTNode(1));
        }
        node.setType(arg0);
        naryDataManager.setNode(node);
    }

    @Given("an invalid n-ary node with type {string} and {int} child\\(ren)")
    public void anInvalidNAryNodeWithTypeAndChildRen(String arg0, int arg1) {
        ASTNode node = new ASTNode();
        for(int i = 0; i<arg1; i++) {
            node.addChild(new ASTNode(1));
        }
        node.setType(arg0);
        naryDataManager.setNode(node);
    }

    @When("the NaryASTConverter is created for the ERODE conversion")
    public void theNaryASTConverterIsCreatedForTheERODEConversion() {
        try {
            NaryASTConverter naryASTConverter = NaryManager.create(naryDataManager.getNode());
            naryDataManager.setNaryASTConverter(naryASTConverter);
        } catch (Exception e) {
            naryDataManager.setException(e);
        }
    }

    @Then("an ERODE update function representing a N-ary AND operation was created")
    public void anERODEUpdateFunctionRepresentingANAryANDOperationWasCreated() {
        NaryASTConverter naryASTConverter = naryDataManager.getNaryASTConverter();
        Assert.assertNotNull(naryASTConverter);
        IUpdateFunction updateFunction = naryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        AnalyzeNaryUpdateFunction(updateFunction, BooleanConnector.AND);
    }

    @Then("an ERODE update function representing a N-ary OR operation was created")
    public void anERODEUpdateFunctionRepresentingANAryOROperationWasCreated() {
        NaryASTConverter naryASTConverter = naryDataManager.getNaryASTConverter();
        Assert.assertNotNull(naryASTConverter);
        IUpdateFunction updateFunction = naryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        AnalyzeNaryUpdateFunction(updateFunction, BooleanConnector.OR);
    }

    @Then("an ERODE update function representing a N-ary XOR operation was created")
    public void anERODEUpdateFunctionRepresentingANAryXOROperationWasCreated() {
        NaryASTConverter naryASTConverter = naryDataManager.getNaryASTConverter();
        Assert.assertNotNull(naryASTConverter);
        IUpdateFunction updateFunction = naryASTConverter.getUpdateFunction();
        Assert.assertNotNull(updateFunction);
        AnalyzeNaryUpdateFunction(updateFunction, BooleanConnector.XOR);
    }

    private void AnalyzeNaryUpdateFunction(IUpdateFunction updateFunction, BooleanConnector connector) {
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, updateFunction.getClass());
        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr)updateFunction;
        Assert.assertEquals(connector,andExpr.getOperator());

        Assert.assertTrue(IsBooleanConstant(andExpr.getSecond()));

        IUpdateFunction leftNode = andExpr.getFirst();
        if(!IsBooleanConstant(leftNode))
            AnalyzeNaryUpdateFunction(leftNode, connector);
    }

    private boolean IsBooleanConstant(IUpdateFunction updateFunction) {
        Class<?> nodeClass = updateFunction.getClass();
        return nodeClass.equals(TrueUpdateFunction.class) || nodeClass.equals(FalseUpdateFunction.class);
    }
}
