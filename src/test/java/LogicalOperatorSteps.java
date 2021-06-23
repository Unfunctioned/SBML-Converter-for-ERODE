import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.updatefunctions.*;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.apache.commons.math3.analysis.function.Exp;
import org.junit.Assert;
import sbml.conversion.operators.Operator;

public class LogicalOperatorSteps {
    private static final Operator operator = new Operator();
    private IUpdateFunction booleanExpression;

    @When("the AND-operation is created")
    public void theANDOperationIsCreated() {
        try {
            Expression ec = Expression.getInstance();
            booleanExpression = operator.And(ec.getX(),ec.getY());
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @When("the OR-operation is created")
    public void theOROperationIsCreated() {
        try {
            Expression ec = Expression.getInstance();
            booleanExpression = operator.Or(ec.getX(),ec.getY());
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @When("the NOT-operation is created")
    public void theNOTOperationIsCreated() {
        try {
            Expression ec = Expression.getInstance();
            booleanExpression = operator.Not(ec.getX());
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @When("the XOR-operation is created")
    public void theXOROperationIsCreated() {
        try {
            Expression expr = Expression.getInstance();
            booleanExpression = operator.Xor(expr.getX(),expr.getY());
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @When("the IMPLIES-operation is created")
    public void theIMPLIESOperationIsCreated() {
        try {
            Expression expr = Expression.getInstance();
            booleanExpression = operator.Implies(expr.getX(),expr.getY());
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @Then("a boolean update function representing the AND-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheANDOperationIsCreatedSuccessfully() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());

        Expression expression = Expression.getInstance();

        Assert.assertNotNull(booleanExpression);
        Assert.assertEquals(booleanExpression.getClass(),BooleanUpdateFunctionExpr.class);
        BooleanUpdateFunctionExpr functionExpr = (BooleanUpdateFunctionExpr) booleanExpression;
        Assert.assertEquals(expression.getX(),functionExpr.getFirst());
        Assert.assertEquals(expression.getY(),functionExpr.getSecond());
        Assert.assertEquals(BooleanConnector.AND,functionExpr.getOperator());
    }

    @Then("a boolean update function representing the OR-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheOROperationIsCreatedSuccessfully() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());

        Expression expression = Expression.getInstance();

        Assert.assertNotNull(booleanExpression);
        Assert.assertEquals(booleanExpression.getClass(),BooleanUpdateFunctionExpr.class);
        BooleanUpdateFunctionExpr functionExpr = (BooleanUpdateFunctionExpr) booleanExpression;
        Assert.assertEquals(expression.getX(), functionExpr.getFirst());
        Assert.assertEquals(expression.getY(),functionExpr.getSecond());
        Assert.assertEquals(BooleanConnector.OR,functionExpr.getOperator());
    }

    @Then("a boolean update function representing the NOT-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheNOTOperationIsCreatedSuccessfully() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());

        Expression expression = Expression.getInstance();

        Assert.assertNotNull(booleanExpression);
        Assert.assertEquals(booleanExpression.getClass(), NotBooleanUpdateFunction.class);

        NotBooleanUpdateFunction expr = (NotBooleanUpdateFunction) booleanExpression;
        Assert.assertEquals(expression.getX(),expr.getInnerUpdateFunction());
    }

    @Then("a boolean update function representing the XOR-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheXOROperationIsCreatedSuccessfully() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());

        Expression expression = Expression.getInstance();

        Assert.assertNotNull(booleanExpression);
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, booleanExpression.getClass());

        //Assert outerExpression [() && !()]
        BooleanUpdateFunctionExpr outerExpr = (BooleanUpdateFunctionExpr) booleanExpression;
        Assert.assertEquals(BooleanConnector.AND, outerExpr.getOperator());

        //Assert left term [(x || y)]
        IUpdateFunction lhs = outerExpr.getFirst();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, lhs.getClass());
        BooleanUpdateFunctionExpr orExpr = (BooleanUpdateFunctionExpr) lhs;
        Assert.assertEquals(expression.getX(), orExpr.getFirst());
        Assert.assertEquals(expression.getY(), orExpr.getSecond());
        Assert.assertEquals(BooleanConnector.OR, orExpr.getOperator());

        //Assert right term [!(x && y)]
        IUpdateFunction rhs = (NotBooleanUpdateFunction) outerExpr.getSecond();
        Assert.assertEquals(NotBooleanUpdateFunction.class, rhs.getClass());

        NotBooleanUpdateFunction notExpr = (NotBooleanUpdateFunction) rhs;
        IUpdateFunction innerExpr = notExpr.getInnerUpdateFunction();
        Assert.assertEquals(BooleanUpdateFunctionExpr.class, innerExpr.getClass());
        BooleanUpdateFunctionExpr andExor = (BooleanUpdateFunctionExpr) innerExpr;
        Assert.assertEquals(expression.getX(), andExor.getFirst());
        Assert.assertEquals(expression.getY(), andExor.getSecond());
        Assert.assertEquals(BooleanConnector.AND, andExor.getOperator());
    }

    @Then("a boolean update function representing the IMPLIES-operation is created successfully")
    public void aBooleanUpdateFunctionRepresentingTheIMPLIESOperationIsCreatedSuccessfully() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());

        Expression expression = Expression.getInstance();

        Assert.assertNotNull(booleanExpression);
        Assert.assertEquals(booleanExpression.getClass(),BooleanUpdateFunctionExpr.class);
        BooleanUpdateFunctionExpr functionExpr = (BooleanUpdateFunctionExpr) booleanExpression;
        Assert.assertEquals(expression.getX(),functionExpr.getFirst());
        Assert.assertEquals(expression.getY(),functionExpr.getSecond());
        Assert.assertEquals(BooleanConnector.IMPLIES,functionExpr.getOperator());
    }
}
