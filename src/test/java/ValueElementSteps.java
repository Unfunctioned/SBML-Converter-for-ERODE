import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.FalseUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.TrueUpdateFunction;
import org.junit.Assert;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.operators.Operator;

public class ValueElementSteps {
    private static final Operator operator = new Operator();
    private static final ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();

    private ASTNode speciesReference;
    private ASTNode booleanConstant;

    private IUpdateFunction erodeReference;
    private IUpdateFunction erodeConstant;

    @Given("a species with name {string} to be referenced")
    public void aSpeciesWithNameToBeReferenced(String arg0) {
        speciesReference = new ASTNode(arg0);
    }

    @Given("an ASTNode with value {int} representing a constant")
    public void anASTNodeWithValueRepresentingAConstant(int arg0) {
        booleanConstant = new ASTNode(arg0);
    }

    @Given("an ASTNode of type {string} representing a boolean constant")
    public void anASTNodeOfTypeRepresentingABooleanConstant(String arg0) {
        booleanConstant = new ASTNode();
        booleanConstant.setType(arg0);
    }

    @When("the Reference-operation is created")
    public void theReferenceOperationIsCreated() {
        try {
            erodeReference = operator.Reference(speciesReference);
        } catch (Exception e) {
            exceptionCollector.setException(e);
        }
    }

    @When("the constant is created")
    public void theConstantIsCreated() {
        try {
            erodeConstant = operator.Constant(booleanConstant);
        } catch (Exception e) {
            exceptionCollector.setException(e);
        }
    }

    @When("the boolean value is created")
    public void theBooleanValueIsCreated() {
        try {
            erodeConstant = operator.BooleanValue(booleanConstant);
        } catch (Exception e) {
            exceptionCollector.setException(e);
        }
    }

    @Then("an update function representing the FALSE constant is created")
    public void anUpdateFunctionRepresentingTheFALSEConstantIsCreated() {
        Assert.assertNotNull(erodeConstant);
        Assert.assertEquals(FalseUpdateFunction.class, erodeConstant.getClass());
    }

    @Then("an update function representing the TRUE constant is created")
    public void anUpdateFunctionRepresentingTheTRUEConstantIsCreated() {
        Assert.assertNotNull(erodeConstant);
        Assert.assertEquals(TrueUpdateFunction.class, erodeConstant.getClass());
    }

    @Then("an update function representing the variable {string} is created successfully")
    public void anUpdateFunctionRepresentingTheVariableIsCreatedSuccessfully(String arg0) {
        Assert.assertNotNull(erodeReference);
        Assert.assertEquals(ReferenceToNodeUpdateFunction.class, erodeReference.getClass());
        Assert.assertEquals(arg0, erodeReference.toString());
    }
}
