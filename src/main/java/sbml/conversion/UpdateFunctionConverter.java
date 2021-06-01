package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import org.sbml.jsbml.ext.qual.Input;
import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.Transition;
import java.util.LinkedHashMap;

public class UpdateFunctionConverter {

    private FunctionTermConverter functionTermConverter;

    private ListOf<Transition> sbmlTransitions;
    private LinkedHashMap<String, IUpdateFunction> erodeUpdateFunctions;

    public UpdateFunctionConverter(@NotNull ListOf<Transition> listOfTransitions) {
        this.functionTermConverter = new FunctionTermConverter();
        this.erodeUpdateFunctions = new LinkedHashMap<>();
        this.sbmlTransitions = listOfTransitions;
        this.toErodeFormat();
    }

    private void toErodeFormat() {
        for(Transition t : this.sbmlTransitions) {
            functionTermConverter.convert(t, this.erodeUpdateFunctions);
            System.out.println();
        }
    }

    public LinkedHashMap<String, IUpdateFunction> getErodeUpdateFunctions() {
        return this.erodeUpdateFunctions;
    }
}