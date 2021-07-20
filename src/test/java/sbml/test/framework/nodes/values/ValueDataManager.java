package sbml.test.framework.nodes.values;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.value.ValueASTConverter;
import sbml.test.framework.TestDataManager;

public class ValueDataManager extends TestDataManager {
    private ASTNode node;

    private IUpdateFunction updateFunction;

    private ValueASTConverter valueASTConverter;

    public ASTNode getNode() {
        if(node == null && valueASTConverter != null)
            this.node = valueASTConverter.getExpressionAST();
        return node;
    }

    public void setNode(ASTNode node) {
        this.node = node;
    }

    public IUpdateFunction getUpdateFunction() {
        if(updateFunction == null && valueASTConverter != null)
            this.updateFunction = valueASTConverter.getUpdateFunction();
        return updateFunction;
    }

    public void setUpdateFunction(IUpdateFunction updateFunction) {
        this.updateFunction = updateFunction;
    }

    public ValueASTConverter getValueASTConverter() {
        return valueASTConverter;
    }

    public void setValueASTConverter(ValueASTConverter valueASTConverter) {
        this.valueASTConverter = valueASTConverter;
    }
}
