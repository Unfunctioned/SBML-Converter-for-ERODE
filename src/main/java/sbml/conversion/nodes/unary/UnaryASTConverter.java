package sbml.conversion.nodes.unary;

import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.NodeConverter;

public abstract class UnaryASTConverter extends NodeConverter {

    public static UnaryASTConverter create(ASTNode node) {
        return new UnaryReader(node);
    }

    public static UnaryASTConverter create(NotBooleanUpdateFunction updateFunction) {
        return new UnaryWriter(updateFunction);
    }

    protected NodeConverter child;

    public UnaryASTConverter(ASTNode node) {
        super(node);
    }

    public UnaryASTConverter(NotBooleanUpdateFunction updateFunction) {
        super(updateFunction);
    }
}
