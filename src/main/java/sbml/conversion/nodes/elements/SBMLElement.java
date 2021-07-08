package sbml.conversion.nodes.elements;

import com.kitfox.svg.A;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.TrueUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.nodes.operators.ASTNodeBuilder;

public class SBMLElement implements IElement<IUpdateFunction,ASTNode> {

    private ASTNodeBuilder builder;

    public SBMLElement() {
        this.builder = new ASTNodeBuilder();
    }
    @Override
    public ASTNode reference(IUpdateFunction node) {
        Class<?> classType = node.getClass();
        if(!classType.equals(ReferenceToNodeUpdateFunction.class))
            throw new IllegalArgumentException("Given update function is not a reference");
        ReferenceToNodeUpdateFunction reference = (ReferenceToNodeUpdateFunction) node;
        return builder.reference(reference.toString());
    }

    @Override
    public ASTNode constant(IUpdateFunction node) {
        Class<?> classType = node.getClass();
        if(classType.equals(TrueUpdateFunction.class))
            return builder.integer(1);
        else
            return builder.integer(0);
    }

    @Override
    public ASTNode booleanConstant(IUpdateFunction node) {
        Class<?> classType = node.getClass();
        if(classType.equals(TrueUpdateFunction.class))
            return builder.integer(1);
        else
            return builder.integer(0);
    }
}
