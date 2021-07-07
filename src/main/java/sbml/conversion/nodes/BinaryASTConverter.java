package sbml.conversion.nodes;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ASTNode.Type;
import sbml.conversion.espressions.operators.ErodeOperator;

public class BinaryASTConverter extends NodeConverter {

    private final NodeConverter leftChild;
    private final NodeConverter rightChild;

    public BinaryASTConverter(ASTNode node) {
        super(node);
        this.leftChild = NodeConverter.create(currentNode.getChild(0));
        this.rightChild = NodeConverter.create(currentNode.getChild(1));
        convertSBML();
    }

    public BinaryASTConverter(BooleanUpdateFunctionExpr updateFunction) {
        super(updateFunction);
        this.leftChild = NodeConverter.create(updateFunction.getFirst());
        this.rightChild = NodeConverter.create(updateFunction.getSecond());
        convertERODE();
    }

    @Override
    protected void convertSBML() {
        Type type = currentNode.getType();
        ErodeOperator operator = (ErodeOperator) this.operator;
        switch (type) {
            case LOGICAL_AND:
                this.updateFunction = operator.And(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            case LOGICAL_OR:
                this.updateFunction = operator.Or(leftChild.getUpdateFunction(),rightChild.getUpdateFunction());
                break;
            case LOGICAL_XOR:
                this.updateFunction = operator.Xor(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            case LOGICAL_IMPLIES:
                this.updateFunction = operator.Implies(leftChild.getUpdateFunction(),rightChild.getUpdateFunction());
                break;
            case RELATIONAL_EQ:
                this.updateFunction = operator.Equals(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            case RELATIONAL_NEQ:
                this.updateFunction = operator.NotEquals(leftChild.getUpdateFunction(), rightChild.getUpdateFunction());
                break;
            default:
                throw new IllegalArgumentException("Invalid type name");
        }
    }

    @Override
    protected void convertERODE() {

    }
}
