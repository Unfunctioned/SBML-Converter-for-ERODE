package sbml.conversion.nodes;

import org.sbml.jsbml.ASTNode;

public class BinaryASTConverter extends NodeConverter {

    private NodeConverter leftChild;
    private NodeConverter rightChild;

    public BinaryASTConverter(ASTNode node) {
        super(node);
        this.leftChild = NodeConverter.create(currentNode.getChild(0));
        this.rightChild = NodeConverter.create(currentNode.getChild(1));
        convert();
    }

    @Override
    protected void convert() {
        ASTNode.Type type = currentNode.getType();
        switch (type.name()) {
            case "LOGICAL_OR":
                this.updateFunction = operator.Or(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            case "LOGICAL_AND":
                this.updateFunction = operator.And(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            case "LOGICAL_XOR":
                this.updateFunction = operator.Xor(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            case "RELATIONAL_EQ":
                this.updateFunction = operator.Equals(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            case "RELATIONAL_NEG":
                this.updateFunction = operator.NotEquals(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            default:
                throw new IllegalArgumentException("Type name did not match any case!");
        }
    }
}
