package sbml.conversion.operators;

import it.imt.erode.booleannetwork.updatefunctions.FalseUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.TrueUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.operators.interfaces.IValueElement;

public class ValueElement implements IValueElement {

    public IUpdateFunction Reference(ASTNode node) {
        return new ReferenceToNodeUpdateFunction(node.getName());
    }

    public IUpdateFunction Constant(ASTNode node) {
        switch (node.getInteger()) {
            case 1:
                return new TrueUpdateFunction();
            case 0:
                return new FalseUpdateFunction();
            default:
                throw new IllegalArgumentException("Given node value is not a boolean value");
        }
    }

    @Override
    public IUpdateFunction BooleanValue(ASTNode node) {
        ASTNode.Type type = node.getType();
        switch (type.name()) {
            case "CONSTANT_FALSE":
                return new FalseUpdateFunction();
            case "CONSTANT_TRUE":
                return new TrueUpdateFunction();
            default:
                throw new IllegalArgumentException("Given node is not a boolean constant");
        }
    }
}
