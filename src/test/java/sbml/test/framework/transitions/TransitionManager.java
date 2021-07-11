package sbml.test.framework.transitions;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import org.sbml.jsbml.ext.qual.Input;
import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.Transition;
import sbml.conversion.transitions.ITransitionConverter;
import sbml.test.framework.TestDataManager;

import java.util.LinkedHashMap;

public class TransitionManager extends TestDataManager {
    private SBMLDocument sbmlDocument;

    private ListOf<Transition> transitions;
    private ListOf<Input> inputs;
    private ListOf<Output> outputs;
    private ListOf<FunctionTerm> functionTerms;
    private Transition transition;

    private ITransitionConverter transitionConverter;

    private LinkedHashMap<String, IUpdateFunction> updateFunctions;
    private IUpdateFunction updateFunction;

    public SBMLDocument getSbmlDocument() {
        return sbmlDocument;
    }

    public void setSbmlDocument(SBMLDocument sbmlDocument) {
        this.sbmlDocument = sbmlDocument;
    }

    public ListOf<Transition> getTransitions() {
        if(transitions == null && transitionConverter != null)
            this.transitions = transitionConverter.getSbmlTransitions();
        return transitions;
    }

    public void setTransitions(ListOf<Transition> transitions) {
        this.transitions = transitions;
    }

    public ITransitionConverter getTransitionConverter() {
        return transitionConverter;
    }

    public void setTransitionConverter(ITransitionConverter transitionConverter) {
        this.transitionConverter = transitionConverter;
    }

    public LinkedHashMap<String, IUpdateFunction> getUpdateFunctions() {
        if(updateFunctions == null && transitionConverter != null)
            this.updateFunctions = transitionConverter.getErodeUpdateFunctions();
        return updateFunctions;
    }

    public void setUpdateFunctions(LinkedHashMap<String, IUpdateFunction> updateFunctions) {
        this.updateFunctions = updateFunctions;
    }

    public ListOf<Input> getInputs() {
        return inputs;
    }

    public void setInputs(ListOf<Input> inputs) {
        this.inputs = inputs;
    }

    public ListOf<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(ListOf<Output> outputs) {
        this.outputs = outputs;
    }

    public ListOf<FunctionTerm> getFunctionTerms() {
        return functionTerms;
    }

    public void setFunctionTerms(ListOf<FunctionTerm> functionTerms) {
        this.functionTerms = functionTerms;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public IUpdateFunction getUpdateFunction() {
        return updateFunction;
    }

    public void setUpdateFunction(IUpdateFunction updateFunction) {
        this.updateFunction = updateFunction;
    }
}
