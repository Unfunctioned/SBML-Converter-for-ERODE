package sbml.conversion.operators.interfaces;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;

public interface IRelationalOperator {

    IUpdateFunction Equals(IUpdateFunction x, IUpdateFunction y);

    IUpdateFunction NotEquals(IUpdateFunction x, IUpdateFunction y);
}
