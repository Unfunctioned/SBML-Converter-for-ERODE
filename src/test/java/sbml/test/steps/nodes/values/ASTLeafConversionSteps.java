package sbml.test.steps.nodes.values;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.FalseUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.TrueUpdateFunction;
import org.junit.Assert;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.value.ValueASTConverter;
import sbml.test.framework.TestDataManager;
import sbml.test.framework.nodes.values.ValueDataManager;
import static sbml.test.framework.TestDataManager.Type;

public class ASTLeafConversionSteps {

    private ValueDataManager valueManager;

    @Given("a ValueManager has been initialized")
    public void aValueManagerHasBeenInitialized() {
        TestDataManager.setInstance(Type.VALUES);
        valueManager = (ValueDataManager) TestDataManager.getInstance();
    }

    @Given("an ASTNode representing the variable {string}")
    public void anASTNodeWithRepresentingTheVariable(String arg0) {
        valueManager.setNode(new ASTNode(arg0));
    }

    @Given("an update function representing the variable {string}")
    public void anUpdateFunctionRepresentingTheVariable(String arg0) {
        IUpdateFunction reference = new ReferenceToNodeUpdateFunction(arg0);
        valueManager.setUpdateFunction(reference);
    }

    @Given("an ASTNode representing the value {int}")
    public void anASTNodeRepresentingTheValue(int arg0) {
        valueManager.setNode(new ASTNode(arg0));
    }

    @Given("an ASTNode representing the boolean constant {string}")
    public void anASTNodeRepresentingTheBooleanConstant(String arg0) {
        ASTNode node = new ASTNode();
        node.setType(arg0);
        valueManager.setNode(node);
    }

    @Given("an ASTNode of unknown format")
    public void anASTNodeOfUnknownFormat() {
        ASTNode node = new ASTNode();
        node.setType("UNKNOWN");
        valueManager.setNode(node);
    }

    @Given("an update function representing the constant FALSE")
    public void anUpdateFunctionRepresentingTheConstantFALSE() {
        valueManager.setUpdateFunction(new FalseUpdateFunction());
    }

    @Given("an update function representing the constant TRUE")
    public void anUpdateFunctionRepresentingTheConstantTRUE() {
        valueManager.setUpdateFunction(new TrueUpdateFunction());
    }

    @When("the ValueASTConverter is created for the ERODE conversion")
    public void theValueASTConverterIsCreatedForTheERODEConversion() {
        try {
            ASTNode node = valueManager.getNode();
            valueManager.setValueASTConverter(ValueASTConverter.create(node));
        } catch (Exception e) {
            valueManager.setException(e);
        }
    }

    @When("the ValueASTConverter is created for the SBML conversion")
    public void theValueASTConverterIsCreatedForTheSBMLConversion() {
        try {
            IUpdateFunction updateFunction = valueManager.getUpdateFunction();
            valueManager.setValueASTConverter(ValueASTConverter.create(updateFunction));
        } catch (Exception e) {
            valueManager.setException(e);
        }
    }

    @Then("the ValueASTConverter creation succeeds")
    public void theValueASTConverterCreationSucceeds() {
        Assert.assertNull(valueManager.getException());
        Assert.assertNotNull(valueManager.getValueASTConverter());
    }

    @Then("the converted object is available in ERODE format")
    public void theConvertedObjectIsAvailableInERODEFormat() {
        Assert.assertNotNull(valueManager.getUpdateFunction());
    }

    @Then("the converted object is available in SBML format")
    public void theConvertedObjectIsAvailableInSBMLFormat() {
        Assert.assertNotNull(valueManager.getNode());
    }

    @Then("the ERODE object represents the boolean value {int}")
    public void theERODEObjectRepresentTheValue(int arg0) {
        IUpdateFunction updateFunction = valueManager.getUpdateFunction();
        switch (arg0) {
            case 0:
                Assert.assertEquals(FalseUpdateFunction.class, updateFunction.getClass());
                break;
            case 1:
                Assert.assertEquals(TrueUpdateFunction.class, updateFunction.getClass());
                break;
            default:
                Assert.fail("Given value is not a boolean value");
        }
    }

    @Then("the SBML object represents the integer {int}")
    public void theSBMLObjectRepresentsTheInteger(int arg0) {
        ASTNode node = valueManager.getNode();
        Assert.assertEquals(node.getType(),ASTNode.Type.INTEGER);
        Assert.assertEquals(node.getInteger(),arg0);
    }
}
