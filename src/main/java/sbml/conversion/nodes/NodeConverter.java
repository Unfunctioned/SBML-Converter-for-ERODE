package sbml.conversion.nodes;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.configurations.Strings;
import sbml.conversion.nodes.binary.BinaryASTConverter;
import sbml.conversion.nodes.unary.UnaryASTConverter;
import sbml.conversion.nodes.value.ValueASTConverter;

public abstract class NodeConverter implements INodeConverter {

    protected ASTNode currentNode;
    protected IUpdateFunction updateFunction;

    public NodeConverter(ASTNode node) {
        this.currentNode = node;
    }

    public NodeConverter(IUpdateFunction updateFunction) {
        this.updateFunction = updateFunction;
    }

    protected abstract void convert();

    @Override
    public IUpdateFunction getUpdateFunction() {
        return updateFunction;
    }

    @Override
    public ASTNode getExpressionAST() {
        return currentNode;
    }
}
