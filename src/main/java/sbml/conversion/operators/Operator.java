package sbml.conversion.operators;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.operators.interfaces.ILogicalOperator;
import sbml.conversion.operators.interfaces.IRelationalOperator;
import sbml.conversion.operators.interfaces.IValueElement;

public class Operator implements ILogicalOperator, IRelationalOperator, IValueElement {

    private final LogicalOperator logicalOperator;
    private final RelationalOperator relationalOperator;
    private final ValueElement valueElement;

    public Operator() {
        this.logicalOperator = new LogicalOperator();
        this.relationalOperator = new RelationalOperator(logicalOperator);
        this.valueElement = new ValueElement();
    }

    @Override
    public IUpdateFunction And(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return logicalOperator.And(x,y);
    }

    @Override
    public IUpdateFunction Or(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return logicalOperator.Or(x,y);
    }

    @Override
    public IUpdateFunction Not(@NotNull IUpdateFunction x) {
        return logicalOperator.Not(x);
    }

    @Override
    public IUpdateFunction Xor(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return logicalOperator.Xor(x,y);
    }

    @Override
    public IUpdateFunction Equals(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return relationalOperator.Equals(x,y);
    }

    @Override
    public IUpdateFunction NotEquals(@NotNull IUpdateFunction x, @NotNull IUpdateFunction y) {
        return relationalOperator.NotEquals(x,y);
    }

    @Override
    public IUpdateFunction Reference(ASTNode node) {
        return valueElement.Reference(node);
    }

    @Override
    public IUpdateFunction Constant(ASTNode node) {
        return valueElement.Constant(node);
    }
}
