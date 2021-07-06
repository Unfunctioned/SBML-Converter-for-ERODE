package sbml.conversion.operators;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.FalseUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.jetbrains.annotations.NotNull;
import sbml.conversion.operators.interfaces.ILogicalOperator;

public class LogicalOperator implements ILogicalOperator {

    public IUpdateFunction And(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x, y, BooleanConnector.AND);
    }

    public IUpdateFunction Or(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x, y, BooleanConnector.OR);
    }

    public IUpdateFunction Not(@NotNull IUpdateFunction x) {
        return new NotBooleanUpdateFunction(x);
    }

    public IUpdateFunction Xor(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.XOR);
    }

    public IUpdateFunction Implies(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.IMPLIES);
    }
}
