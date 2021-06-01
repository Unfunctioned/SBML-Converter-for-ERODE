package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.*;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import org.sbml.jsbml.ext.qual.Input;
import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.Transition;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FunctionTermConverter {

    public FunctionTermConverter() { }

    public void convert(Transition t, LinkedHashMap<String, IUpdateFunction> updateFunctions) {
        ListOf<Input> inputs = t.getListOfInputs();
        ListOf<Output> outputs = t.getListOfOutputs();
        ListOf<FunctionTerm> functionTerms = t.getListOfFunctionTerms();
        for(FunctionTerm f : functionTerms) {
            if(!f.isDefaultTerm() && f.isSetResultLevel() && f.getResultLevel() == 1) {
                for(Output o : outputs)
                    updateFunctions.put(o.getQualitativeSpecies(),convertUpdateFunction(f));
            }
        }
    }

    private IUpdateFunction convertUpdateFunction(FunctionTerm functionTerm) {
        ASTNode node = functionTerm.getMath();
        return convertASTNode(node);
    }

    private IUpdateFunction convertASTNode(ASTNode node) {
        List<ASTNode> nodes = node.getListOfNodes();
        ASTNode.Type type = node.getType();
        switch (type.name()) {
            case "RELATIONAL_EQ":
                return new ReferenceToNodeUpdateFunction(nodes.get(0).getName());
            case "LOGICAL_OR":
                return new BooleanUpdateFunctionExpr(convertASTNode(nodes.get(0)),
                        convertASTNode(nodes.get(1)), BooleanConnector.OR);
            case "LOGICAL_AND":
                return new BooleanUpdateFunctionExpr(convertASTNode(nodes.get(0)),
                        convertASTNode(nodes.get(1)), BooleanConnector.AND);
            default:
                throw new IllegalArgumentException("type name did not match any case!");
        }
    }

    /*private IUpdateFunction convertDefaultFunction(int resultLevel) {
        switch (resultLevel) {
            case 1:
                return new TrueUpdateFunction();
            default:
                return new FalseUpdateFunction();
        }
    }*/
}
