package sbml.conversion.transitions;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.Transition;

import java.util.LinkedHashMap;

public class TransitionManager {
    public static ITransitionConverter create(@NotNull ListOf<Transition> transitions) {
        return new TransitionReader(transitions);
    }

    public static ITransitionConverter create(@NotNull LinkedHashMap<String, IUpdateFunction> updateFunctions) {
        return new TransitionWriter(updateFunctions);
    }
}
