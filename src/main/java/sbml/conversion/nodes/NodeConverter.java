package sbml.conversion.nodes;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.configurations.Strings;
import sbml.conversion.nodes.binary.BinaryASTConverter;
import sbml.conversion.nodes.unary.UnaryASTConverter;
import sbml.conversion.nodes.value.ValueASTConverter;

public abstract class NodeConverter implements INodeConverter {

    public static NodeConverter create(ASTNode node) {
        switch (node.getChildCount()) {
            case 2:
                return BinaryASTConverter.create(node);
            case 1:
                return UnaryASTConverter.create(node);
            case 0:
                return ValueASTConverter.create(node);
            default:
                throw new IllegalArgumentException("A node cannot have more than 2 children");
        }
    }

    public static NodeConverter create(IUpdateFunction updateFunction) {
        Class<?> classType = updateFunction.getClass();
        String className = classType.getSimpleName();
        switch (className) {
            case Strings.BINARY_EXPRESSION:
                return BinaryASTConverter.create((BooleanUpdateFunctionExpr)updateFunction);
            case Strings.NEGATION:
                return UnaryASTConverter.create((NotBooleanUpdateFunction)updateFunction);
            case Strings.REFERENCE:
            case Strings.TRUE:
            case Strings.FALSE:
                return ValueASTConverter.create(updateFunction);
            default:
                throw new IllegalArgumentException("Unknown update function type");
        }
    }

    protected ASTNode currentNode;
    protected IUpdateFunction updateFunction;

    public NodeConverter(ASTNode node) {
        this.currentNode = node;
    }

    public NodeConverter(IUpdateFunction updateFunction) {
        this.updateFunction = updateFunction;
    }

    protected abstract void convert();

    @Override
    public IUpdateFunction getUpdateFunction() {
        return updateFunction;
    }

    @Override
    public ASTNode getExpressionAST() {
        return currentNode;
    }
}
