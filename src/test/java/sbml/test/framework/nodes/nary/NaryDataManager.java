package sbml.test.framework.nodes.nary;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.nary.NaryASTConverter;
import sbml.test.framework.TestDataManager;

public class NaryDataManager extends TestDataManager {
    private ASTNode node;

    private NaryASTConverter naryASTConverter;

    private IUpdateFunction updateFunction;

    public ASTNode getNode() {
        if(node == null && naryASTConverter != null)
            this.node = naryASTConverter.getExpressionAST();
        return node;
    }

    public void setNode(ASTNode node) {
        this.node = node;
    }

    public NaryASTConverter getNaryASTConverter() {
        return naryASTConverter;
    }

    public void setNaryASTConverter(NaryASTConverter naryASTConverter) {
        this.naryASTConverter = naryASTConverter;
    }

    public IUpdateFunction getUpdateFunction() {
        if(updateFunction == null && naryASTConverter != null)
            this.updateFunction = naryASTConverter.getUpdateFunction();
        return updateFunction;
    }

    public void setUpdateFunction(IUpdateFunction updateFunction) {
        this.updateFunction = updateFunction;
    }
}
