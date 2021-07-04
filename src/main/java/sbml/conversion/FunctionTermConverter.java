package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import sbml.conversion.nodes.NodeConverter;

public class FunctionTermConverter {

    public IUpdateFunction toErode(FunctionTerm functionTerm) {
        ASTNode node = functionTerm.getMath();
        NodeConverter converter = NodeConverter.create(node);
        return converter.getUpdateFunction();
    }
}
