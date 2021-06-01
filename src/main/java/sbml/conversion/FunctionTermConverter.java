package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.FalseUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.TrueUpdateFunction;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import org.sbml.jsbml.ext.qual.Input;
import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.Transition;

import java.util.LinkedHashMap;
import java.util.Map;

public class FunctionTermConverter {

    public FunctionTermConverter() { }

    public void convert(Transition t, LinkedHashMap<String, IUpdateFunction> updateFunctions) {
        ListOf<Input> inputs = t.getListOfInputs();
        ListOf<Output> outputs = t.getListOfOutputs();
        ListOf<FunctionTerm> functionTerms = t.getListOfFunctionTerms();
        for(FunctionTerm f : functionTerms) {
            if(f.isDefaultTerm() && f.isSetResultLevel()) {
                for(Output o : outputs)
                    updateFunctions.put(o.getQualitativeSpecies(),convertDefaultFunction(f.getResultLevel()));
            }
        }
    }

    private IUpdateFunction convertDefaultFunction(int resultLevel) {
        switch (resultLevel) {
            case 1:
                return new TrueUpdateFunction();
            default:
                return new FalseUpdateFunction();
        }
    }
}
