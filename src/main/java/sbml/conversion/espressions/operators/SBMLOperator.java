package sbml.conversion.espressions.operators;

import org.sbml.jsbml.ASTNode;
import sbml.conversion.builders.ASTNodeBuilder;

public class SBMLOperator implements IOperator<ASTNode> {

    private ASTNodeBuilder builder;

    public SBMLOperator() {
        this.builder = new ASTNodeBuilder();
    }

    @Override
    public ASTNode Not(ASTNode x) {
        return builder.unary(x,ASTNode.Type.LOGICAL_NOT);
    }

    @Override
    public ASTNode And(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.LOGICAL_AND);
    }

    @Override
    public ASTNode Or(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.LOGICAL_OR);
    }

    @Override
    public ASTNode Xor(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.LOGICAL_XOR);
    }

    @Override
    public ASTNode Implies(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.LOGICAL_IMPLIES);
    }

    @Override
    public ASTNode Equals(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.RELATIONAL_EQ);
    }

    @Override
    public ASTNode NotEquals(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.RELATIONAL_NEQ);
    }
}
