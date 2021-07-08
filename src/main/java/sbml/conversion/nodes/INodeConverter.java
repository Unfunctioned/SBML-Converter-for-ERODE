package sbml.conversion.nodes;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ASTNode;

public interface INodeConverter {

    IUpdateFunction getUpdateFunction();

    ASTNode getExpressionAST();
}
