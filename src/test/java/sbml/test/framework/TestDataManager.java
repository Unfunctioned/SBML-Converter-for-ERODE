package sbml.test.framework;

import sbml.test.framework.document.DocumentDataManager;
import sbml.test.framework.model.ModelDataManager;
import sbml.test.framework.nodes.binary.BinaryDataManager;
import sbml.test.framework.nodes.nary.NaryDataManager;
import sbml.test.framework.nodes.unary.UnaryDataManager;
import sbml.test.framework.nodes.values.ValueDataManager;
import sbml.test.framework.qualmodel.QualModelDataManager;
import sbml.test.framework.species.SpeciesDataManager;
import sbml.test.framework.transitions.TransitionDataManager;

public abstract  class TestDataManager {

    public enum Type {
        VALUES,
        UNARY,
        BINARY,
        NARY,
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
                dataManager = new ValueDataManager();
                break;
            case UNARY:
                dataManager = new UnaryDataManager();
                break;
            case BINARY:
                dataManager = new BinaryDataManager();
                break;
            case NARY:
                dataManager = new NaryDataManager();
                break;
            case TRANSITION:
                dataManager = new TransitionDataManager();
                break;
            case SPECIES:
                dataManager = new SpeciesDataManager();
                break;
            case QUAL:
                dataManager = new QualModelDataManager();
                break;
            case MODEL:
                dataManager = new ModelDataManager();
                break;
            case DOCUMENT:
                dataManager = new DocumentDataManager();
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
