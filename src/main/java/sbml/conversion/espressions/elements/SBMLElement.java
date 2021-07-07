package sbml.conversion.espressions.elements;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;

public class SBMLElement implements IElement<IUpdateFunction,ASTNode> {
    @Override
    public ASTNode Reference(IUpdateFunction node) {
        return null;
    }

    @Override
    public ASTNode Constant(IUpdateFunction node) {
        return null;
    }

    @Override
    public ASTNode BooleanValue(IUpdateFunction node) {
        return null;
    }
}
