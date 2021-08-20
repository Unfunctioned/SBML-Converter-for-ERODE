package sbml.conversion.nodes.unary;

import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.INodeConverter;
import sbml.conversion.nodes.NodeConverter;

public abstract class UnaryASTConverter extends NodeConverter {

    protected INodeConverter child;

    public UnaryASTConverter(ASTNode node) {
        super(node);
    }

    public UnaryASTConverter(NotBooleanUpdateFunction updateFunction) {
        super(updateFunction);
    }
}
