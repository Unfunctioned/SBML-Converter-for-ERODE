import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.junit.Assert;
import sbml.conversion.espressions.operators.ErodeOperator;
import sbml.conversion.espressions.Format;
import sbml.conversion.espressions.operators.Operator;

public class RelationalOperatorSteps {

    private static final ErodeOperator OPERATOR = (ErodeOperator) Operator.create(Format.ERODE);
    private IUpdateFunction relationalExpression;


    @When("the Equals-operation is created")
    public void theEqualsOperationIsCreated() {
        try {
            Expression expression = Expression.getInstance();
            relationalExpression = OPERATOR.Equals(expression.getX(),expression.getY());
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @When("the NotEquals-operation is created")
    public void theNotEqualsOperationIsCreated() {
        try {
            Expression expression = Expression.getInstance();
            relationalExpression = OPERATOR.NotEquals(expression.getX(),expression.getY());
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

        Assert.assertEquals(BooleanUpdateFunctionExpr.class, relationalExpression.getClass());
        BooleanUpdateFunctionExpr eqExpr = (BooleanUpdateFunctionExpr) relationalExpression;
        Assert.assertEquals(BooleanConnector.EQ, eqExpr.getOperator());
    }

    @Then("a boolean update function representing the NotEquals-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheNotEqualsOperationIsCreatedSuccessfully() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());
        Assert.assertNotNull(relationalExpression);

        Assert.assertEquals(BooleanUpdateFunctionExpr.class, relationalExpression.getClass());
        BooleanUpdateFunctionExpr neqExpr = (BooleanUpdateFunctionExpr) relationalExpression;
        Assert.assertEquals(BooleanConnector.NEQ,neqExpr.getOperator());
    }
}
