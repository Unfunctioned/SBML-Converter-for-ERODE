package sbml.demos;

import org.sbml.jsbml.ASTNode;
import java.util.List;

public class ASTNodeLinking {

    public static void main(String[] args) {
        //Creating an AST
        ASTNode root = new ASTNode(ASTNode.Type.LOGICAL_AND);
        ASTNode leftChild = new ASTNode(ASTNode.Type.CONSTANT_FALSE);
        ASTNode rightChild = new ASTNode(ASTNode.Type.CONSTANT_TRUE);

        root.addChild(leftChild);
        root.addChild(rightChild);


        //To retrieve children of the AST root
        List<ASTNode> children;
        if(root.getAllowsChildren()) {
            int childCount = root.getChildCount();
            children = root.getChildren();

            //To retrieve a specific child
            ASTNode child = (ASTNode) root.getChildAt(0);
        }
    }
}
