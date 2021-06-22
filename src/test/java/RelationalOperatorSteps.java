import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.junit.Assert;
import sbml.conversion.operators.Operator;

public class RelationalOperatorSteps {

    private static final Operator operator = new Operator();
    private IUpdateFunction relationalExpression;


    @When("the Equals-operation is created")
    public void theEqualsOperationIsCreated() {
        try {
            Expression expression = Expression.getInstance();
            relationalExpression = operator.Equals(expression.getX(),expression.getY());
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @When("the NotEquals-operation is created")
    public void theNotEqualsOperationIsCreated() {
        try {
            Expression expression = Expression.getInstance();
            relationalExpression = operator.NotEquals(expression.getX(),expression.getY());
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @Then("a boolean update function representing the Equals-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheEqualsOperationIsCreatedSuccessfully() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());
        Assert.assertNotNull(relationalExpression);

        Expression expression = Expression.getInstance();

        //Assert outer expression [() && ()]
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, relationalExpression.getClass());
        BooleanUpdateFunctionExpr outerExpr = (BooleanUpdateFunctionExpr) relationalExpression;
        Assert.assertEquals(BooleanConnector.AND, outerExpr.getOperator());

        //Assert left term [(x -> y)]
        IUpdateFunction leftExpr = outerExpr.getFirst();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, leftExpr.getClass());
        BooleanUpdateFunctionExpr xImpliesY = (BooleanUpdateFunctionExpr) leftExpr;

        Assert.assertEquals(expression.getX(), xImpliesY.getFirst());
        Assert.assertEquals(expression.getY(), xImpliesY.getSecond());
        Assert.assertEquals(BooleanConnector.IMPLIES, xImpliesY.getOperator());

        //Assert right term [y -> x]
        IUpdateFunction rightExpr = outerExpr.getSecond();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, rightExpr.getClass());
        BooleanUpdateFunctionExpr yImpliesX = (BooleanUpdateFunctionExpr) rightExpr;

        Assert.assertEquals(expression.getY(), yImpliesX.getFirst());
        Assert.assertEquals(expression.getX(), yImpliesX.getSecond());
        Assert.assertEquals(BooleanConnector.IMPLIES, yImpliesX.getOperator());
    }

    @Then("a boolean update function representing the NotEquals-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheNotEqualsOperationIsCreatedSuccessfully() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());
        Assert.assertNotNull(relationalExpression);

        Expression expression = Expression.getInstance();

        Assert.assertEquals(NotBooleanUpdateFunction.class, relationalExpression.getClass());
        NotBooleanUpdateFunction notExpr = (NotBooleanUpdateFunction) relationalExpression;

        /* TODO: Add getInner() to the NotBooleanUpdate class
        //Assert inner expression [() && ()]
        IUpdateFunction innerExpr = notExpr.getInner();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, innerExpr.getClass());
        BooleanUpdateFunctionExpr andExpr = (BooleanUpdateFunctionExpr) innerExpr;
        Assert.assertEquals(BooleanConnector.AND, andExpr.getOperator());

        //Assert left term [(x -> y)]
        IUpdateFunction leftExpr = andExpr.getFirst();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, leftExpr.getClass());
        BooleanUpdateFunctionExpr xImpliesY = (BooleanUpdateFunctionExpr) leftExpr;

        Assert.assertEquals(expression.getX(), xImpliesY.getFirst());
        Assert.assertEquals(expression.getY(), xImpliesY.getSecond());
        Assert.assertEquals(BooleanConnector.IMPLIES, xImpliesY.getOperator());

        //Assert right term [y -> x]
        IUpdateFunction rightExpr = andExpr.getSecond();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, rightExpr.getClass());
        BooleanUpdateFunctionExpr yImpliesX = (BooleanUpdateFunctionExpr) rightExpr;

        Assert.assertEquals(expression.getY(), yImpliesX.getFirst());
        Assert.assertEquals(expression.getX(), yImpliesX.getSecond());
        Assert.assertEquals(BooleanConnector.IMPLIES, yImpliesX.getOperator());
         */
    }
}
