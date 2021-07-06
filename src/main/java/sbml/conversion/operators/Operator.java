package sbml.conversion.operators;

import io.cucumber.java.an.E;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;
import sbml.conversion.operators.interfaces.IOperator;

public class Operator {
    
    public static IOperator create(Format format) {
        if(format.equals(Format.ERODE))
            return new ErodeOperator();
        else {
            return new SBMLOperator();
        }
    }
}
