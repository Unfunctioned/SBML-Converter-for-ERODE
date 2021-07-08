package sbml.conversion.nodes.value;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.NodeConverter;

public abstract class ValueASTConverter extends NodeConverter {

    public static ValueASTConverter create(ASTNode node) {
        return new ValueReader(node);
    }

    public static ValueASTConverter create(IUpdateFunction updateFunction) {
        return new ValueWriter(updateFunction);
    }

    public ValueASTConverter(ASTNode node) {
        super(node);
    }

    public ValueASTConverter(IUpdateFunction updateFunction) {
        super(updateFunction);
    }
}
