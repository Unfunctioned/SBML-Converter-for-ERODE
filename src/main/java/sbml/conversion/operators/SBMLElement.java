package sbml.conversion.operators;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.operators.interfaces.IElement;

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
