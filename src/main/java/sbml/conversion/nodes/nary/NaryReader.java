package sbml.conversion.nodes.nary;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.model.IModelConverter;
import sbml.conversion.nodes.INodeConverter;
import sbml.conversion.nodes.NodeManager;
import sbml.conversion.nodes.operators.ErodeOperator;

public class NaryReader extends NaryASTConverter {

    private ErodeOperator operator;
    private ASTNode.Type type;

    public NaryReader(ASTNode node) {
        super(node);
        for (ASTNode child : currentNode.getChildren())
            children.add(NodeManager.create(child));
        this.operator = new ErodeOperator();
        this.type = currentNode.getType();
        this.convert();
    }

    @Override
    protected void convert() {
        if(children.size() < 2)
            throw new IllegalArgumentException("Nary operators require at least two children");
        INodeConverter firstChild = children.get(0);
        INodeConverter secondChild = children.get(1);
        this.updateFunction = createUpdateFunction(firstChild, secondChild);
        for(int i = 2; i < children.size(); i++) {
            INodeConverter child = children.get(i);
            updateFunction = createUpdateFunction(updateFunction, child);
        }

    }

    private IUpdateFunction createUpdateFunction(INodeConverter first, INodeConverter second) {
        switch (type) {
            case LOGICAL_AND:
                return operator.and(first.getUpdateFunction(), second.getUpdateFunction());
            case LOGICAL_OR:
                return operator.or(first.getUpdateFunction(),second.getUpdateFunction());
            case LOGICAL_XOR:
                return operator.xor(first.getUpdateFunction(), second.getUpdateFunction());
            default:
                throw new IllegalArgumentException("Invalid type name");
        }
    }

    private IUpdateFunction createUpdateFunction(IUpdateFunction naryExpression, INodeConverter child) {
        switch (type) {
            case LOGICAL_AND:
                return operator.and(naryExpression, child.getUpdateFunction());
            case LOGICAL_OR:
                return operator.or(naryExpression,child.getUpdateFunction());
            case LOGICAL_XOR:
                return operator.xor(naryExpression, child.getUpdateFunction());
            default:
                throw new IllegalArgumentException("Invalid type name");
        }
    }
}
