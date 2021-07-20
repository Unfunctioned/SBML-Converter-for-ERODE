package sbml.test.framework.nodes.unary;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.unary.UnaryASTConverter;
import sbml.test.framework.TestDataManager;

public class UnaryDataManager extends TestDataManager {

    private ASTNode node;

    private IUpdateFunction updateFunction;

    private UnaryASTConverter unaryASTConverter;

    public ASTNode getNode() {
        if(this.node == null && unaryASTConverter != null)
            this.node = unaryASTConverter.getExpressionAST();
        return node;
    }

    public void setNode(ASTNode node) {
        this.node = node;
    }

    public IUpdateFunction getUpdateFunction() {
        if(this.updateFunction == null && unaryASTConverter != null)
            this.updateFunction = unaryASTConverter.getUpdateFunction();
        return updateFunction;
    }

    public void setUpdateFunction(IUpdateFunction updateFunction) {
        this.updateFunction = updateFunction;
    }

    public UnaryASTConverter getUnaryASTConverter() {
        return unaryASTConverter;
    }

    public void setUnaryASTConverter(UnaryASTConverter unaryASTConverter) {
        this.unaryASTConverter = unaryASTConverter;
    }
}
