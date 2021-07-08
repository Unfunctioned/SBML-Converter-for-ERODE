package sbml.conversion.functionterm;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import sbml.configurations.SBMLConfiguration;
import sbml.conversion.nodes.NodeConverter;

public class FunctionTermWriter {
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

    private FunctionTermBuilder functionTermBuilder;

    public FunctionTermWriter() {
        super();
        this.functionTermBuilder = new FunctionTermBuilder();
    }

    public ListOf<FunctionTerm> convert(IUpdateFunction updateFunction, int maxLevel) {
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
