package sbml.conversion.transitions;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.Transition;
import sbml.conversion.functionterm.FunctionTermReader;

import java.util.LinkedHashMap;

class TransitionReader extends TransitionConverter {

    private FunctionTermReader reader;

    public TransitionReader(@NotNull ListOf<Transition> listOfTransitions) {
        super(listOfTransitions);
        this.reader = new FunctionTermReader();
        this.erodeUpdateFunctions = convertSBMLTransitions();
    }

    private LinkedHashMap<String, IUpdateFunction> convertSBMLTransitions() {
        LinkedHashMap<String,IUpdateFunction> erodeUpdateFunctions = new LinkedHashMap<>();
        for(Transition t : this.sbmlTransitions) {
            ListOf<Output> outputs = t.getListOfOutputs();
            IUpdateFunction updateFunction = reader.convert(t.getListOfFunctionTerms());
            for(Output o : outputs)
                erodeUpdateFunctions.put(o.getQualitativeSpecies(),updateFunction);
        }
        return erodeUpdateFunctions;
    }
}
