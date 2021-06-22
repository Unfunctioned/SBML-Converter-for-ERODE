package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.Transition;
import sbml.conversion.nodes.NodeConverter;

import java.util.LinkedHashMap;

public class FunctionTermConverter {

    public FunctionTermConverter() {
    }

    public void convert(Transition t, LinkedHashMap<String, IUpdateFunction> updateFunctions) throws Exception {
        ListOf<Output> outputs = t.getListOfOutputs();
        ListOf<FunctionTerm> functionTerms = t.getListOfFunctionTerms();
        for(FunctionTerm f : functionTerms) {
            if(!f.isDefaultTerm() && f.isSetResultLevel() && f.getResultLevel() == 1) {
                for(Output o : outputs)
                    updateFunctions.put(o.getQualitativeSpecies(), convertUpdateFunction(f));
            }
        }
    }

    private IUpdateFunction convertUpdateFunction(FunctionTerm functionTerm) {
        ASTNode node = functionTerm.getMath();
        NodeConverter converter = NodeConverter.create(node);
        return converter.getUpdateFunction();
    }
}
