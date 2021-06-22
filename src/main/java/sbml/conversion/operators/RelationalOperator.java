package sbml.conversion.operators;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import sbml.conversion.operators.interfaces.IRelationalOperator;

public class RelationalOperator implements IRelationalOperator {

    private final LogicalOperator logicalOperator;

    public RelationalOperator(LogicalOperator logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public IUpdateFunction Equals(IUpdateFunction x, IUpdateFunction y) {
        //x <-> y = x->y && y->x
        IUpdateFunction xImpliesY = new BooleanUpdateFunctionExpr(x, y, BooleanConnector.IMPLIES);
        IUpdateFunction yImpliesX = new BooleanUpdateFunctionExpr(y, x, BooleanConnector.IMPLIES);
        return new BooleanUpdateFunctionExpr(xImpliesY, yImpliesX, BooleanConnector.AND);
    }

    public IUpdateFunction NotEquals(IUpdateFunction x, IUpdateFunction y) {
        IUpdateFunction equals = Equals(x,y);
        return logicalOperator.Not(equals);
    }
}

