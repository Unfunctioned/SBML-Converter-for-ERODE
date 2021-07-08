package sbml.conversion.nodes.operators;

import org.sbml.jsbml.ASTNode;

public class SBMLOperator implements IOperator<ASTNode> {

    private ASTNodeBuilder builder;

    public SBMLOperator() {
        this.builder = new ASTNodeBuilder();
    }

    @Override
    public ASTNode not(ASTNode x) {
        return builder.unary(x,ASTNode.Type.LOGICAL_NOT);
    }

    @Override
    public ASTNode and(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.LOGICAL_AND);
    }

    @Override
    public ASTNode or(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.LOGICAL_OR);
    }

    @Override
    public ASTNode xor(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.LOGICAL_XOR);
    }

    @Override
    public ASTNode implies(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.LOGICAL_IMPLIES);
    }

    @Override
    public ASTNode equals(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.RELATIONAL_EQ);
    }

    @Override
    public ASTNode notEquals(ASTNode x, ASTNode y) {
        return builder.binary(x,y,ASTNode.Type.RELATIONAL_NEQ);
    }
}
