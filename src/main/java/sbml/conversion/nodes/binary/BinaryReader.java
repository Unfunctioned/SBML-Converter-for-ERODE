package sbml.conversion.nodes.binary;

import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.NodeManager;
import sbml.conversion.nodes.operators.ErodeOperator;
import sbml.conversion.nodes.NodeConverter;

public class BinaryReader extends BinaryASTConverter {

    private ErodeOperator operator;

    public BinaryReader(ASTNode node) {
        super(node);
        this.leftChild = NodeManager.create(currentNode.getChild(0));
        this.rightChild = NodeManager.create(currentNode.getChild(1));
        this.operator = new ErodeOperator();
        this.convert();
    }

    @Override
    protected void convert() {
        ASTNode.Type type = currentNode.getType();
        switch (type) {
            case LOGICAL_AND:
                this.updateFunction = operator.and(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            case LOGICAL_OR:
                this.updateFunction = operator.or(leftChild.getUpdateFunction(),rightChild.getUpdateFunction());
                break;
            case LOGICAL_XOR:
                this.updateFunction = operator.xor(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            case LOGICAL_IMPLIES:
                this.updateFunction = operator.implies(leftChild.getUpdateFunction(),rightChild.getUpdateFunction());
                break;
            case RELATIONAL_EQ:
                this.updateFunction = operator.equals(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            case RELATIONAL_NEQ:
                this.updateFunction = operator.notEquals(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            default:
                throw new IllegalArgumentException("Invalid type name");
        }
    }
}
