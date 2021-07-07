package sbml.conversion.espressions.operators;

import sbml.conversion.espressions.Format;

public class Operator {
    
    public static IOperator create(Format format) {
        if(format.equals(Format.ERODE))
            return new ErodeOperator();
        else {
            return new SBMLOperator();
        }
    }
}
