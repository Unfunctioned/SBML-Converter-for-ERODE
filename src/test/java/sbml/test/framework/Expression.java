package sbml.test.framework;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;

public class Expression {

    private static final Expression expressionCollector = new Expression();

    public static Expression getInstance() {
        return expressionCollector;
    }

    public static void clear() {
        expressionCollector.setX(null);
        expressionCollector.setY(null);
    }

    private IUpdateFunction x;
    private IUpdateFunction y;

    public IUpdateFunction getX() {
        return x;
    }

    public void setX(IUpdateFunction x) {
        this.x = x;
    }

    public IUpdateFunction getY() {
        return y;
    }

    public void setY(IUpdateFunction y) {
        this.y = y;
    }

}
