package sbml.conversion.nodes.binary;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.INodeConverter;
import sbml.conversion.nodes.NodeConverter;

public abstract class BinaryASTConverter extends NodeConverter {

    protected INodeConverter leftChild;
    protected INodeConverter rightChild;

    public BinaryASTConverter(ASTNode node) {
        super(node);
    }

    public BinaryASTConverter(IUpdateFunction updateFunction) {
        super(updateFunction);
    }

}
