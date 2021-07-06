package sbml.conversion.operators;

import it.imt.erode.booleannetwork.updatefunctions.FalseUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.TrueUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.operators.interfaces.IElement;
import sbml.conversion.operators.interfaces.IOperator;

public class Element {

    public static IElement create(Format format) {
        if(format.equals(Format.ERODE))
            return new ErodeElement();
        else {
            return new SBMLElement();
        }
    }
}
