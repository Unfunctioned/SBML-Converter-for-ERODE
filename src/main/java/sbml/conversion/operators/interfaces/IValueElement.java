package sbml.conversion.operators.interfaces;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;

public interface IValueElement {

    IUpdateFunction Reference(ASTNode node);

    IUpdateFunction Constant(ASTNode node);
}
