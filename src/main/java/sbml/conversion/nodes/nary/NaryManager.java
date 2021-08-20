package sbml.conversion.nodes.nary;

import org.sbml.jsbml.ASTNode;

public class NaryManager {
    public static NaryASTConverter create(ASTNode node) {
        return new NaryReader(node);
    }

}
