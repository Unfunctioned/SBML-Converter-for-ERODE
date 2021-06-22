package sbml.conversion.operators;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.operators.interfaces.IValueElement;

public class ValueElement implements IValueElement {

    public IUpdateFunction Reference(ASTNode node) {
        return new ReferenceToNodeUpdateFunction(node.getName());
    }

    public IUpdateFunction Constant(ASTNode node) {
        switch (node.getInteger()) {
            case 1:
                return new ReferenceToNodeUpdateFunction("true");
            case 0:
                return new ReferenceToNodeUpdateFunction("false");
            default:
                throw new IllegalArgumentException("Given network is not a boolean network");
        }
    }
}
