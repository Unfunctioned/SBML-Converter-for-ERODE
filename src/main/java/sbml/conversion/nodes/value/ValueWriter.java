package sbml.conversion.nodes.value;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import sbml.configurations.Strings;
import sbml.conversion.nodes.elements.SBMLElement;
import sbml.conversion.nodes.operators.ASTNodeBuilder;

public class ValueWriter extends ValueASTConverter {

    private SBMLElement element;

    public ValueWriter(IUpdateFunction updateFunction) {
        super(updateFunction);
        this.element = new SBMLElement();
        this.convert();
    }

    @Override
    protected void convert() {
        Class<?> classType = updateFunction.getClass();
        String className = classType.getSimpleName();
        ASTNodeBuilder builder = new ASTNodeBuilder();
        switch (className) {
            case Strings.REFERENCE:
                this.currentNode = element.reference(updateFunction);
                break;
            case Strings.TRUE:
            case Strings.FALSE:
                this.currentNode = element.booleanConstant(updateFunction);
                break;
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}
