package sbml.conversion.nodes.value;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.NodeConverter;

public abstract class ValueASTConverter extends NodeConverter {

    public ValueASTConverter(ASTNode node) {
        super(node);
    }

    public ValueASTConverter(IUpdateFunction updateFunction) {
        super(updateFunction);
    }
}
