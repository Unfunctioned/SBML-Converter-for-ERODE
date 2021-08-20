package sbml.conversion.nodes.value;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;

public class ValueManager {
    public static ValueASTConverter create(ASTNode node) {
        return new ValueReader(node);
    }

    public static ValueASTConverter create(IUpdateFunction updateFunction) {
        return new ValueWriter(updateFunction);
    }
}
