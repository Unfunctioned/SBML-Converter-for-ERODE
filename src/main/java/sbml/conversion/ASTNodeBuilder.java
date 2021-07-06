package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import org.sbml.jsbml.ASTNode;

public class ASTNodeBuilder {

    public ASTNode convertNodeReference(IUpdateFunction updateFunction) {
        ReferenceToNodeUpdateFunction refExpr = (ReferenceToNodeUpdateFunction) updateFunction;
        return new ASTNode(refExpr.toString());
    }

    public ASTNode convertBinaryNode(ASTNode leftChild, ASTNode rightChild, BooleanUpdateFunctionExpr expr) {
        ASTNode astNode = new ASTNode();
        switch (expr.getOperator()) {
            case AND:
                astNode.setType(ASTNode.Type.LOGICAL_AND);
                break;
            case OR:
                astNode.setType(ASTNode.Type.LOGICAL_OR);
                break;
            case IMPLIES:
                astNode.setType(ASTNode.Type.LOGICAL_IMPLIES);
                break;
            case XOR:
                astNode.setType(ASTNode.Type.LOGICAL_XOR);
                break;
            case EQ:
                astNode.setType(ASTNode.Type.RELATIONAL_EQ);
                break;
            case NEQ:
                astNode.setType(ASTNode.Type.RELATIONAL_NEQ);
                break;
            default:
                throw new IllegalArgumentException("Invalid type");
        }
        astNode.addChild(leftChild);
        astNode.addChild(rightChild);
        return astNode;
    }

    public ASTNode convertNegationNode(ASTNode child) {
        ASTNode node = new ASTNode(child);
        node.setType(ASTNode.Type.LOGICAL_NOT);
        return node;
    }

    public ASTNode createValueNode(int i) {
        return new ASTNode(i);
    }

    public ASTNode createBinaryNode(ASTNode child, ASTNode.Type type) {
        ASTNode node = new ASTNode();
        for(ASTNode n : child.getChildren())
            node.addChild(n);
        node.setType(type);
        return node;
    }
}
