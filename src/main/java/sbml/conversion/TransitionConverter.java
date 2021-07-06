package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import org.sbml.jsbml.ext.qual.Input;
import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.Transition;
import sbml.configurations.SBMLConfiguration;

import java.util.LinkedHashMap;
import java.util.Map;

public class TransitionConverter {
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

    private FunctionTermConverter functionTermConverter;

    private ListOf<Transition> sbmlTransitions;
    private LinkedHashMap<String, IUpdateFunction> erodeUpdateFunctions;

    private InputBuilder inputBuilder;
    private OutputBuilder outputBuilder;

    public TransitionConverter(@NotNull ListOf<Transition> listOfTransitions) {
        this.functionTermConverter = new FunctionTermConverter();
        this.erodeUpdateFunctions = new LinkedHashMap<>();
        this.sbmlTransitions = listOfTransitions;
        this.toErodeFormat();
    }

    private void toErodeFormat() {
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

    public TransitionConverter(LinkedHashMap<String, IUpdateFunction> updateFunctions) {
        this.erodeUpdateFunctions = updateFunctions;
        this.sbmlTransitions = new ListOf<>(CONFIG.getLevel(),CONFIG.getVersion());

        this.inputBuilder = new InputBuilder();
        this.outputBuilder = new OutputBuilder();


        this.functionTermConverter = new FunctionTermConverter();
        this.toSbmlFormat();

    }

    private void toSbmlFormat() {
        int id = 0;
        for (Map.Entry<String, IUpdateFunction> e : erodeUpdateFunctions.entrySet()) {
            Output output = outputBuilder.buildFrom(e.getKey(), id);
            ListOf<Input> inputs = inputBuilder.createInputs(e.getValue());
            ListOf<FunctionTerm> functionTerms = functionTermConverter.toSBML(e.getValue(), 1);
            Transition transition = CreateTransition(inputs,output,functionTerms);
            sbmlTransitions.add(transition);
            id++;
        }
    }

    private Transition CreateTransition(ListOf<Input> inputs, Output output, ListOf<FunctionTerm> functionTerms) {
        ListOf<Output> outputs = new ListOf<>(CONFIG.getLevel(),CONFIG.getVersion());
        Transition t = new Transition(CONFIG.getLevel(),CONFIG.getVersion());
        t.setListOfInputs(inputs);
        t.setListOfOutputs(outputs);
        t.setListOfFunctionTerms(functionTerms);
        return t;
    }

    public LinkedHashMap<String, IUpdateFunction> getErodeUpdateFunctions() {
        return this.erodeUpdateFunctions;
    }

    public ListOf<Transition> getSbmlTransitions() {
        return sbmlTransitions;
    }
}
