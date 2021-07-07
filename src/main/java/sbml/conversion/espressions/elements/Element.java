package sbml.conversion.espressions.elements;

import sbml.conversion.espressions.Format;

public class Element {

    public static IElement create(Format format) {
        if(format.equals(Format.ERODE))
            return new ErodeElement();
        else {
            return new SBMLElement();
        }
    }
}
