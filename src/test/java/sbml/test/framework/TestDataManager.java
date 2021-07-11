package sbml.test.framework;

import sbml.test.framework.nodes.binary.BinaryManager;
import sbml.test.framework.nodes.unary.UnaryManager;
import sbml.test.framework.nodes.values.ValueManager;
import sbml.test.framework.species.SpeciesManager;
import sbml.test.framework.transitions.TransitionManager;

public abstract  class TestDataManager {

    public enum Type {
        VALUES,
        UNARY,
        BINARY,
        TRANSITION,
        SPECIES
    }

    public static TestDataManager dataManager;

    public static TestDataManager getInstance() {
        return dataManager;
    }

    public static void setInstance(Type t) {
        switch (t) {
            case VALUES:
                dataManager = new ValueManager();
                break;
            case UNARY:
                dataManager = new UnaryManager();
                break;
            case BINARY:
                dataManager = new BinaryManager();
                break;
            case TRANSITION:
                dataManager = new TransitionManager();
                break;
            case SPECIES:
                dataManager = new SpeciesManager();
                break;
        }
    }

    protected Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
