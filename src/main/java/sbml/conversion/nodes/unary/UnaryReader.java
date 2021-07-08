package sbml.conversion.nodes.unary;

import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.operators.ErodeOperator;
import sbml.conversion.nodes.NodeConverter;

public class UnaryReader extends UnaryASTConverter {

    private ErodeOperator operator;

    public UnaryReader(ASTNode node) {
        super(node);
        this.operator = new ErodeOperator();
        this.child = NodeConverter.create(node.getChild(0));
        this.convert();
    }

    @Override
    protected void convert() {
        ASTNode.Type type = currentNode.getType();
        switch (type.name()) {
            case "LOGICAL_NOT":
                this.updateFunction = operator.not(child.getUpdateFunction());
                break;
            default:
                throw new IllegalArgumentException("Invalid type name");
        }
    }
}
