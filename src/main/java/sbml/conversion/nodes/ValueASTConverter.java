package sbml.conversion.nodes;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.configurations.Strings;
import sbml.conversion.builders.ASTNodeBuilder;
import sbml.conversion.espressions.elements.ErodeElement;

public class ValueASTConverter extends NodeConverter {

    public ValueASTConverter(ASTNode node) {
        super(node);
        convertSBML();
    }

    public ValueASTConverter(IUpdateFunction updateFunction) {
        super(updateFunction);
        convertERODE();
    }

    @Override
    protected void convertSBML() {
        ASTNode.Type type = currentNode.getType();
        ErodeElement element = (ErodeElement) this.element;
        switch (type) {
            case NAME:
                this.updateFunction = element.Reference(currentNode);
                break;
            case INTEGER:
                this.updateFunction = element.Constant(currentNode);
                break;
            case CONSTANT_FALSE:
            case CONSTANT_TRUE:
                this.updateFunction = element.BooleanValue(currentNode);
                break;
            default:
                throw new IllegalArgumentException("Unknown type name");
        }
    }

    @Override
    protected void convertERODE() {
        Class<?> classType = updateFunction.getClass();
        String className = classType.getSimpleName();
        ASTNodeBuilder builder = new ASTNodeBuilder();
        switch (className) {
            case Strings.REFERENCE:
                this.currentNode = builder.reference((ReferenceToNodeUpdateFunction)updateFunction);
                break;
            case Strings.TRUE:
                this.currentNode = builder.integer(true);
                break;
            case Strings.FALSE:
                this.currentNode = builder.integer(false);
                break;
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}
