package sbml.conversion.nodes;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.configurations.Strings;
import sbml.conversion.ASTNodeBuilder;
import sbml.conversion.operators.ErodeOperator;

public class UnaryASTConverter extends NodeConverter {

    private NodeConverter child;

    public UnaryASTConverter(ASTNode node) {
        super(node);
        this.child = NodeConverter.create(node.getChild(0));
        convertSBML();
    }

    public UnaryASTConverter(NotBooleanUpdateFunction updateFunction) {
        super(updateFunction);
        convertERODE();
    }

    @Override
    protected void convertSBML() {
        ASTNode.Type type = currentNode.getType();
        ErodeOperator operator = (ErodeOperator) this.operator;
        switch (type.name()) {
            case "LOGICAL_NOT":
                this.updateFunction = operator.Not(child.getUpdateFunction());
                break;
            default:
                throw new IllegalArgumentException("Invalid type name");
        }
    }

    @Override
    protected void convertERODE() {
        Class<?> classType = updateFunction.getClass();
        String className = classType.getSimpleName();
        switch (className) {
            case Strings.NEGATION:
                IUpdateFunction subFunction = ((NotBooleanUpdateFunction) updateFunction).getInnerUpdateFunction();
                this.child = NodeConverter.create(subFunction);
                break;
            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }
}
