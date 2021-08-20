package sbml.conversion.nodes.unary;

import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import org.sbml.jsbml.ASTNode;

public class UnaryManager {
    public static UnaryASTConverter create(ASTNode node) {
        return new UnaryReader(node);
    }

    public static UnaryASTConverter create(NotBooleanUpdateFunction updateFunction) {
        return new UnaryWriter(updateFunction);
    }
}
