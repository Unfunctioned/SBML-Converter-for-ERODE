import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.*;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.junit.Assert;
import sbml.conversion.operators.Operator;

public class LogicalOperatorSteps {
    private Operator operator;
    private IUpdateFunction x;
    private IUpdateFunction y;
    private IUpdateFunction booleanExpression;

    @Given("an Operator-object instance")
    public void anOperatorObjectInstance() {
        operator = new Operator();
    }

    @Given("an update function term X in ERODE format")
    public void anUpdateFunctionTermXInERODEFormat() {
        x = new TrueUpdateFunction();
    }

    @Given("an update function term Y in ERODE format")
    public void anUpdateFunctionTermYInERODEFormat() {
        y = new FalseUpdateFunction();
    }

    @When("the AND-operation is created")
    public void theANDOperationIsCreated() {
        try {
            booleanExpression = operator.And(x,y);
        } catch (Exception e) {
            ExceptionCollector.setExceptionInstance(e);
        }
    }

    @When("the OR-operation is created")
    public void theOROperationIsCreated() {
        try {
            booleanExpression = operator.Or(x,y);
        } catch (Exception e) {
            ExceptionCollector.setExceptionInstance(e);
        }
    }

    @When("the NOT-operation is created")
    public void theNOTOperationIsCreated() {
        try {
            booleanExpression = operator.Not(x);
        } catch (Exception e) {
            ExceptionCollector.setExceptionInstance(e);
        }
    }

    @When("the XOR-operation is created")
    public void theXOROperationIsCreated() {
        try {
            booleanExpression = operator.Xor(x,y);
        } catch (Exception e) {
            ExceptionCollector.setExceptionInstance(e);
        }
    }

    @Then("a boolean update function representing the AND-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheANDOperationIsCreatedSuccessfully() {
        Assert.assertNotNull(booleanExpression);
        Assert.assertEquals(booleanExpression.getClass(),BooleanUpdateFunctionExpr.class);
        BooleanUpdateFunctionExpr expr = (BooleanUpdateFunctionExpr) booleanExpression;
        Assert.assertEquals(x,expr.getFirst());
        Assert.assertEquals(y,expr.getSecond());
        Assert.assertEquals(BooleanConnector.AND,expr.getOperator());
    }

    @Then("a boolean update function representing the OR-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheOROperationIsCreatedSuccessfully() {
        Assert.assertNotNull(booleanExpression);
        Assert.assertEquals(booleanExpression.getClass(),BooleanUpdateFunctionExpr.class);
        BooleanUpdateFunctionExpr expr = (BooleanUpdateFunctionExpr) booleanExpression;
        Assert.assertEquals(x,expr.getFirst());
        Assert.assertEquals(y,expr.getSecond());
        Assert.assertEquals(BooleanConnector.OR,expr.getOperator());
    }

    @Then("a boolean update function representing the NOT-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheNOTOperationIsCreatedSuccessfully() {
        Assert.assertNotNull(booleanExpression);
        Assert.assertEquals(booleanExpression.getClass(), NotBooleanUpdateFunction.class);

        /* TODO: Add getInner() to the NotBooleanUpdate class
        NotBooleanUpdateFunction expr = (NotBooleanUpdateFunction) booleanExpression;
        Assert.assertEquals(x,expr.getInner());
        */
    }

    @Then("a boolean update function representing the XOR-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheXOROperationIsCreatedSuccessfully() {
        Assert.assertNotNull(booleanExpression);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, booleanExpression.getClass());

        //Assert outerExpression [() && !()]
        BooleanUpdateFunctionExpr outerExpr = (BooleanUpdateFunctionExpr) booleanExpression;
        Assert.assertEquals(BooleanConnector.AND, outerExpr.getOperator());

        //Assert left term [(x || y)]
        IUpdateFunction lhs = outerExpr.getFirst();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, lhs.getClass());
        BooleanUpdateFunctionExpr orExpr = (BooleanUpdateFunctionExpr) lhs;
        Assert.assertEquals(x, orExpr.getFirst());
        Assert.assertEquals(y, orExpr.getSecond());
        Assert.assertEquals(BooleanConnector.OR, orExpr.getOperator());

        //Assert right term [!(x && y)]
        IUpdateFunction rhs = (NotBooleanUpdateFunction) outerExpr.getSecond();
        Assert.assertEquals(NotBooleanUpdateFunction.class, rhs.getClass());

        /* TODO: Add getInner() to the NotBooleanUpdate class
        NotBooleanUpdateFunction notExpr = (NotBooleanUpdateFunction) rhs;
        IUpdateFunction innerExpr = notExpr.getInner();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, innerExpr.getClass());
        BooleanUpdateFunctionExpr andExor = (BooleanUpdateFunctionExpr) innerExpr;
        Assert.assertEquals(x, andExor.getFirst());
        Assert.assertEquals(y, andExor.getSecond());
        Assert.assertEquals(BooleanConnector.AND, andExor.getOperator());
        */
    }
}
