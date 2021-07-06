package sbml.conversion.operators.interfaces;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;

public interface IElement<T,T2> {

    T2 Reference(T node);

    T2 Constant(T node);

    T2 BooleanValue(T node);
}
