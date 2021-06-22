package sbml.conversion.nodes;

import org.sbml.jsbml.ASTNode;

public class ValueASTConverter extends NodeConverter {

    public ValueASTConverter(ASTNode node) {
        super(node);
        convert();
    }

    @Override
    protected void convert() {
        ASTNode.Type type = currentNode.getType();
        switch (type.name()) {
            case "NAME":
                this.updateFunction = operator.Reference(currentNode);
                break;
            case "INTEGER":
                this.updateFunction = operator.Constant(currentNode);
                break;
            default:
                throw new IllegalArgumentException("Type name did not match any case!");
        }
    }
}
