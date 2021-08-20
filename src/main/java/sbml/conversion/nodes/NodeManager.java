package sbml.conversion.nodes;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.configurations.Strings;
import sbml.conversion.nodes.binary.BinaryASTConverter;
import sbml.conversion.nodes.binary.BinaryManager;
import sbml.conversion.nodes.nary.NaryASTConverter;
import sbml.conversion.nodes.nary.NaryManager;
import sbml.conversion.nodes.unary.UnaryASTConverter;
import sbml.conversion.nodes.unary.UnaryManager;
import sbml.conversion.nodes.value.ValueASTConverter;
import sbml.conversion.nodes.value.ValueManager;

public class NodeManager {
    public static INodeConverter create(ASTNode node) {
        switch (node.getChildCount()) {
            case 2:
                return BinaryManager.create(node);
            case 1:
                return UnaryManager.create(node);
            case 0:
                return ValueManager.create(node);
            default:
                return NaryManager.create(node);
        }
    }

    public static INodeConverter create(IUpdateFunction updateFunction) {
        Class<?> classType = updateFunction.getClass();
        String className = classType.getSimpleName();
        switch (className) {
            case Strings.BINARY_EXPRESSION:
                return BinaryManager.create((BooleanUpdateFunctionExpr)updateFunction);
            case Strings.NEGATION:
                return UnaryManager.create((NotBooleanUpdateFunction)updateFunction);
            case Strings.REFERENCE:
            case Strings.TRUE:
            case Strings.FALSE:
                return ValueManager.create(updateFunction);
            default:
                throw new IllegalArgumentException("Unknown update function type");
        }
    }
}
