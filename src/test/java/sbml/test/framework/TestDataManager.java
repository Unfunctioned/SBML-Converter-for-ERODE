package sbml.test.framework;

import sbml.test.framework.document.DocumentManager;
import sbml.test.framework.model.ModelManager;
import sbml.test.framework.nodes.binary.BinaryManager;
import sbml.test.framework.nodes.unary.UnaryManager;
import sbml.test.framework.nodes.values.ValueManager;
import sbml.test.framework.qualmodel.QualModelManager;
import sbml.test.framework.species.SpeciesManager;
import sbml.test.framework.transitions.TransitionManager;

public abstract  class TestDataManager {

    public enum Type {
        VALUES,
        UNARY,
        BINARY,
        TRANSITION,
        SPECIES,
        QUAL,
        MODEL,
        DOCUMENT
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
            case QUAL:
                dataManager = new QualModelManager();
                break;
            case MODEL:
                dataManager = new ModelManager();
                break;
            case DOCUMENT:
                dataManager = new DocumentManager();
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
