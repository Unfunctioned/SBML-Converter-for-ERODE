package sbml.conversion.nodes;

import org.sbml.jsbml.ASTNode;

public class UnaryASTConverter extends NodeConverter {

    private NodeConverter child;

    public UnaryASTConverter(ASTNode node) {
        super(node);
        this.child = NodeConverter.create(node.getChild(0));
        convert();
    }

    @Override
    protected void convert() {
        ASTNode.Type type = currentNode.getType();
        switch (type.name()) {
            case "LOGICAL_NOT":
                this.updateFunction = operator.Not(child.getUpdateFunction());
                break;
            default:
                throw new IllegalArgumentException("Type name did not match any case!");
        }
    }
}
