package sbml.conversion.transitions;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.Transition;

import java.util.LinkedHashMap;

public interface ITransitionConverter {

    LinkedHashMap<String, IUpdateFunction> getErodeUpdateFunctions();

    ListOf<Transition> getSbmlTransitions();

}
