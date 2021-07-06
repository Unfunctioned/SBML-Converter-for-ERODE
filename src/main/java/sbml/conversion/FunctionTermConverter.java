package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.*;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import sbml.configurations.SBMLConfiguration;
import sbml.conversion.nodes.NodeConverter;

public class FunctionTermConverter {
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

    private ASTNodeBuilder astNodeBuilder;
    private FunctionTermBuilder functionTermBuilder;

    public FunctionTermConverter() {
        this.astNodeBuilder = new ASTNodeBuilder();
        this.functionTermBuilder = new FunctionTermBuilder();
    }

    public IUpdateFunction convertSBMLFunctionTerms(ListOf<FunctionTerm> listOfFunctionTerms) {
        FunctionTerm functionTerm = getResultLevel(listOfFunctionTerms,1);
        ASTNode node = functionTerm.getMath();
        NodeConverter converter = NodeConverter.create(node);
        return converter.getUpdateFunction();
    }

    private FunctionTerm getResultLevel(ListOf<FunctionTerm> functionTerms, int level) {
        for(FunctionTerm f : functionTerms) {
            if(f.isSetResultLevel() && f.getResultLevel() == level)
                return f;
        }
        throw new IllegalArgumentException("No function term with result level " + level + " found");
    }

    public ListOf<FunctionTerm> convertERODEUpdateFunctions(IUpdateFunction updateFunction, int maxLevel) {
        ListOf<FunctionTerm> functionTerms = new ListOf<>(CONFIG.getLevel(),CONFIG.getVersion());
        ASTNode astNode = this.convertUpdateFunction(updateFunction);
        for(int i = maxLevel; i > 0; i--) {
            FunctionTerm functionTerm = functionTermBuilder.createFunctionTerm(i, astNode);
            functionTerms.add(functionTerm);
        }

        FunctionTerm defaultTerm = functionTermBuilder.createDefaultTerm();
        functionTerms.add(defaultTerm);
        return functionTerms;
    }

    private ASTNode convertUpdateFunction(IUpdateFunction updateFunction) {
        NodeConverter nodeConverter = NodeConverter.create(updateFunction);
        return nodeConverter.getExpressionAST();
    }
}
