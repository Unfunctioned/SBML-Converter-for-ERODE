package sbml.conversion.functionterm;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import sbml.conversion.nodes.INodeConverter;
import sbml.conversion.nodes.NodeConverter;
import sbml.conversion.nodes.NodeManager;

public class FunctionTermReader {

    public IUpdateFunction convert(ListOf<FunctionTerm> functionTerms) {
        FunctionTerm functionTerm = getResultLevel(functionTerms,1);
        ASTNode node = functionTerm.getMath();
        INodeConverter converter = NodeManager.create(node);
        return converter.getUpdateFunction();
    }

    private FunctionTerm getResultLevel(ListOf<FunctionTerm> functionTerms, int level) {
        for(FunctionTerm f : functionTerms) {
            if(f.isSetResultLevel() && f.getResultLevel() == level)
                return f;
        }
        throw new IllegalArgumentException("No function term with result level " + level + " found");
    }
}
