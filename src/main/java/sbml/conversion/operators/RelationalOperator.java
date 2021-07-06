package sbml.conversion.operators;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.jetbrains.annotations.NotNull;
import sbml.conversion.operators.interfaces.IRelationalOperator;

public class RelationalOperator implements IRelationalOperator {

    private final LogicalOperator logicalOperator;

    public RelationalOperator(@NotNull LogicalOperator logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public IUpdateFunction Equals(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.EQ);
    }

    public IUpdateFunction NotEquals(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x,y,BooleanConnector.NEQ);
    }
}

