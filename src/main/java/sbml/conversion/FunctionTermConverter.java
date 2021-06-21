package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.Transition;

import java.util.LinkedHashMap;
import java.util.List;

public class FunctionTermConverter {

    public FunctionTermConverter() { }

    public void convert(Transition t, LinkedHashMap<String, IUpdateFunction> updateFunctions) {
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
        return convertASTNode(node);
    }

    private IUpdateFunction convertASTNode(ASTNode node) {
        List<ASTNode> nodes = node.getListOfNodes();
        ASTNode.Type type = node.getType();
        switch (type.name()) {
            case "LOGICAL_OR":
                return UpdateFunctionBuilder.Or(convertASTNode(nodes.get(0)),convertASTNode(nodes.get(1)));
            case "LOGICAL_AND":
                return UpdateFunctionBuilder.And(convertASTNode(nodes.get(0)),convertASTNode(nodes.get(1)));
            case "LOGICAL_NOT":
                return UpdateFunctionBuilder.Not(convertASTNode(nodes.get(0)));
            case "LOGICAL_XOR":
                return UpdateFunctionBuilder.Xor(convertASTNode(nodes.get(0)),convertASTNode(nodes.get(1)));
            case "RELATIONAL_EQ":
                return UpdateFunctionBuilder.Equals(convertASTNode(nodes.get(0)), convertASTNode(nodes.get(1)));
            case "RELATIONAL_NEG":
                return UpdateFunctionBuilder.NotEquals(convertASTNode(nodes.get(0)),convertASTNode(nodes.get(1)));
            case "NAME":
                return UpdateFunctionBuilder.Reference(node);
            case "INTEGER":
                return UpdateFunctionBuilder.Constant(node);
            default:
                throw new IllegalArgumentException("type name did not match any case!");
        }
    }
}
