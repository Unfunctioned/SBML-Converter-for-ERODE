package sbml.conversion.nodes;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.configurations.Strings;
import sbml.conversion.espressions.elements.Element;
import sbml.conversion.espressions.Format;
import sbml.conversion.espressions.operators.Operator;
import sbml.conversion.espressions.elements.IElement;
import sbml.conversion.espressions.operators.IOperator;

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

    public static NodeConverter create(IUpdateFunction updateFunction) {
        Class<?> classType = updateFunction.getClass();
        String className = classType.getSimpleName();
        switch (className) {
            case Strings.BINARY_EXPRESSION:
                return new BinaryASTConverter((BooleanUpdateFunctionExpr)updateFunction);
            case Strings.NEGATION:
                return new UnaryASTConverter((NotBooleanUpdateFunction)updateFunction);
            case Strings.REFERENCE:
            case Strings.TRUE:
            case Strings.FALSE:
                return new ValueASTConverter(updateFunction);
            default:
                throw new IllegalArgumentException("Unknown update function type");
        }
    }

    protected final IOperator operator;
    protected final IElement element;

    protected ASTNode currentNode;
    protected IUpdateFunction updateFunction;

    public NodeConverter(ASTNode node) {
        this.operator = Operator.create(Format.ERODE);
        this.element = Element.create(Format.ERODE);
        this.currentNode = node;
    }

    public NodeConverter(IUpdateFunction updateFunction) {
        this.operator = Operator.create(Format.SBML);
        this.element = Element.create(Format.SBML);
        this.updateFunction = updateFunction;
    }

    protected abstract void convertSBML();

    protected abstract void convertERODE();

    public IUpdateFunction getUpdateFunction() {
        return updateFunction;
    }

    public ASTNode getExpressionAST() {
        return currentNode;
    }
}
