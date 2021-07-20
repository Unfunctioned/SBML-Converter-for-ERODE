package sbml.test.framework.nodes.binary;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.binary.BinaryASTConverter;
import sbml.test.framework.TestDataManager;

public class BinaryDataManager extends TestDataManager {

    private ASTNode node;

    private BinaryASTConverter binaryASTConverter;

    private IUpdateFunction updateFunction;

    public ASTNode getNode() {
        if(node == null && binaryASTConverter != null)
            this.node = binaryASTConverter.getExpressionAST();
        return node;
    }

    public void setNode(ASTNode node) {
        this.node = node;
    }

    public BinaryASTConverter getBinaryASTConverter() {
        return binaryASTConverter;
    }

    public void setBinaryASTConverter(BinaryASTConverter binaryASTConverter) {
        this.binaryASTConverter = binaryASTConverter;
    }

    public IUpdateFunction getUpdateFunction() {
        if(updateFunction == null && binaryASTConverter != null)
            this.updateFunction = binaryASTConverter.getUpdateFunction();
        return updateFunction;
    }

    public void setUpdateFunction(IUpdateFunction updateFunction) {
        this.updateFunction = updateFunction;
    }
}
