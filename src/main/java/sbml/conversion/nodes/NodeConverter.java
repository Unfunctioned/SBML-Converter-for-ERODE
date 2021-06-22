package sbml.conversion.nodes;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.operators.Operator;

public abstract class NodeConverter {

    public static NodeConverter create(ASTNode node) {
        switch (node.getChildCount()) {
            case 2:
                return new BinaryASTConverter(node);
            case 1:
                return new UnaryASTConverter(node);
            case 0:
                return new ValueASTConverter(node);
            default:
                throw new IllegalArgumentException("A node cannot have more than 2 children");
        }
    }

    protected final Operator operator;
    protected final ASTNode currentNode;
    protected IUpdateFunction updateFunction;

    public NodeConverter(ASTNode node) {
        this.operator = new Operator();
        this.currentNode = node;
    }
    protected abstract void convert();

    public IUpdateFunction getUpdateFunction() {
        return updateFunction;
    }
}
