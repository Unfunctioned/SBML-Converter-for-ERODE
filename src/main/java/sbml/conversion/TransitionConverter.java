package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.Transition;

import java.util.LinkedHashMap;

public class TransitionConverter {

    private FunctionTermConverter functionTermConverter;

    private ListOf<Transition> sbmlTransitions;
    private LinkedHashMap<String, IUpdateFunction> erodeUpdateFunctions;

    public TransitionConverter(@NotNull ListOf<Transition> listOfTransitions) throws Exception {
        this.functionTermConverter = new FunctionTermConverter();
        this.erodeUpdateFunctions = new LinkedHashMap<>();
        this.sbmlTransitions = listOfTransitions;
        this.toErodeFormat();
    }

    private void toErodeFormat() throws Exception {
        for(Transition t : this.sbmlTransitions) {
            ListOf<Output> outputs = t.getListOfOutputs();
            IUpdateFunction updateFunction = functionTermConverter.toErode(getResultLevel(t.getListOfFunctionTerms(),1));
            for(Output o : outputs)
                erodeUpdateFunctions.put(o.getQualitativeSpecies(),updateFunction);
        }
    }

    private FunctionTerm getResultLevel(ListOf<FunctionTerm> functionTerms, int level) {
        for(FunctionTerm f : functionTerms) {
            if(f.isSetResultLevel() && f.getResultLevel() == 1)
                return f;
        }
        throw new IllegalArgumentException("No function term with result level " + level + " found");
    }

    public LinkedHashMap<String, IUpdateFunction> getErodeUpdateFunctions() {
        return this.erodeUpdateFunctions;
    }
}
