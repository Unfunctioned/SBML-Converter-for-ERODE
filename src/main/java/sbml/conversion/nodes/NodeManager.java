package sbml.conversion.nodes;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.configurations.Strings;
import sbml.conversion.nodes.binary.BinaryASTConverter;
import sbml.conversion.nodes.nary.NaryASTConverter;
import sbml.conversion.nodes.unary.UnaryASTConverter;
import sbml.conversion.nodes.value.ValueASTConverter;

public class NodeManager {
    public static INodeConverter create(ASTNode node) {
        switch (node.getChildCount()) {
            case 2:
                return BinaryASTConverter.create(node);
            case 1:
                return UnaryASTConverter.create(node);
            case 0:
                return ValueASTConverter.create(node);
            default:
                return NaryASTConverter.create(node);
        }
    }

    public static INodeConverter create(IUpdateFunction updateFunction) {
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
}
