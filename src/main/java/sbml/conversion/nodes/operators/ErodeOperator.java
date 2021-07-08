package sbml.conversion.nodes.operators;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;

public class ErodeOperator implements IOperator<IUpdateFunction> {

    @Override
    public IUpdateFunction not(IUpdateFunction x) {
        return new NotBooleanUpdateFunction(x);
    }

    @Override
    public IUpdateFunction and(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x, y, BooleanConnector.AND);
    }

    @Override
    public IUpdateFunction or(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x, y, BooleanConnector.OR);
    }

    @Override
    public IUpdateFunction xor(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.XOR);
    }

    @Override
    public IUpdateFunction implies(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.IMPLIES);
    }

    @Override
    public IUpdateFunction equals(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.EQ);
    }

    @Override
    public IUpdateFunction notEquals(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.NEQ);
    }
}
