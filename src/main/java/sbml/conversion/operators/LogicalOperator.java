package sbml.conversion.operators;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import sbml.conversion.operators.interfaces.ILogicalOperator;

public class LogicalOperator implements ILogicalOperator {

    public IUpdateFunction And(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x, y, BooleanConnector.AND);
    }

    public IUpdateFunction Or(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x, y, BooleanConnector.OR);
    }

    public IUpdateFunction Not(IUpdateFunction x) {
        //Not(X) is equivalent to (X -> false)
        return new BooleanUpdateFunctionExpr(x, new ReferenceToNodeUpdateFunction("false"), BooleanConnector.IMPLIES);
    }

    public IUpdateFunction Xor(IUpdateFunction x, IUpdateFunction y) {
        //(x || y) && !(x && y)
        IUpdateFunction xOrY = new BooleanUpdateFunctionExpr(x, y, BooleanConnector.OR);
        IUpdateFunction xAndY = new BooleanUpdateFunctionExpr(x,y, BooleanConnector.AND);
        return new BooleanUpdateFunctionExpr(xOrY, xAndY, BooleanConnector.AND);
    }
}
