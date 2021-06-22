import io.cucumber.java.en.Given;
import it.imt.erode.booleannetwork.updatefunctions.FalseUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.TrueUpdateFunction;

public class CommonOperatorSteps {

    @Given("an update function term X in ERODE format")
    public void anUpdateFunctionTermXInERODEFormat() {
        Expression expressionCollector = Expression.getInstance();
        expressionCollector.setX(new TrueUpdateFunction());
    }

    @Given("an update function term Y in ERODE format")
    public void anUpdateFunctionTermYInERODEFormat() {
        Expression expressionCollector = Expression.getInstance();
        expressionCollector.setY(new FalseUpdateFunction());
    }

}
