package sbml.conversion.builders;

import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import org.sbml.jsbml.ASTNode;

public class ASTNodeBuilder {

    public ASTNode unary(ASTNode x, ASTNode.Type type) {
        ASTNode node = new ASTNode(type);
        node.addChild(x);
        return node;
    }

    public ASTNode binary(ASTNode x, ASTNode y, ASTNode.Type type) {
        ASTNode node = new ASTNode(type);
        node.addChild(x);
        node.addChild(y);
        return node;
    }

    public ASTNode reference(ReferenceToNodeUpdateFunction updateFunction) {
        return new ASTNode(updateFunction.toString());
    }

    public ASTNode integer(boolean b) {
        if(b)
            return new ASTNode(1);
        else
            return new ASTNode(0);
    }
}
