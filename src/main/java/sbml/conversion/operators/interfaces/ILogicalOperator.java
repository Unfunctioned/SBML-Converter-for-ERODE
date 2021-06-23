package sbml.conversion.operators.interfaces;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;

public interface ILogicalOperator {

    IUpdateFunction And(IUpdateFunction x, IUpdateFunction y);

    IUpdateFunction Or(IUpdateFunction x, IUpdateFunction y);

    IUpdateFunction Not(IUpdateFunction x);

    IUpdateFunction Xor(IUpdateFunction x, IUpdateFunction y);

    IUpdateFunction Implies(IUpdateFunction x, IUpdateFunction y);
}
