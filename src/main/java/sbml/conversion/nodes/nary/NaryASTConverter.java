package sbml.conversion.nodes.nary;

import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.INodeConverter;
import sbml.conversion.nodes.NodeConverter;

import java.util.ArrayList;
import java.util.List;

public abstract class NaryASTConverter extends NodeConverter {

    protected List<INodeConverter> children;

    public NaryASTConverter(ASTNode node) {
        super(node);
        children = new ArrayList<>();
    }

}
