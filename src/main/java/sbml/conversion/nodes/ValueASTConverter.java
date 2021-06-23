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
            case "CONSTANT_FALSE":
            case "CONSTANT_TRUE":
                this.updateFunction = operator.BooleanValue(currentNode);
                break;
            default:
                throw new IllegalArgumentException("Unknown type name");
        }
    }
}
