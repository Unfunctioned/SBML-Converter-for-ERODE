package sbml.conversion.espressions.operators;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import sbml.conversion.espressions.operators.IOperator;

public class ErodeOperator implements IOperator<IUpdateFunction> {

    @Override
    public IUpdateFunction Not(IUpdateFunction x) {
        return new NotBooleanUpdateFunction(x);
    }

    @Override
    public IUpdateFunction And(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x, y, BooleanConnector.AND);
    }

    @Override
    public IUpdateFunction Or(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x, y, BooleanConnector.OR);
    }

    @Override
    public IUpdateFunction Xor(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.XOR);
    }

    @Override
    public IUpdateFunction Implies(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.IMPLIES);
    }

    @Override
    public IUpdateFunction Equals(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.EQ);
    }

    @Override
    public IUpdateFunction NotEquals(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.NEQ);
    }
}
