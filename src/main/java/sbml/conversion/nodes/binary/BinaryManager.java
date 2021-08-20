package sbml.conversion.nodes.binary;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import org.sbml.jsbml.ASTNode;

public class BinaryManager {
    public static BinaryASTConverter create(ASTNode node) {
        return new BinaryReader(node);
    }

    public static BinaryASTConverter create(BooleanUpdateFunctionExpr updateFunction) {
        return new BinaryWriter(updateFunction);
    }
}
