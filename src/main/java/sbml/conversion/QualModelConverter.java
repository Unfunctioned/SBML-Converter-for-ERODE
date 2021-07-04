package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ext.qual.QualModelPlugin;

import java.util.LinkedHashMap;

public class QualModelConverter {

    private QualModelPlugin sbmlQualModel;
    private SpeciesConverter speciesConverter;
    private TransitionConverter transitionConverter;

    public QualModelConverter(@NotNull QualModelPlugin qualModel) throws Exception {
        this.sbmlQualModel = qualModel;
        this.speciesConverter = new SpeciesConverter(qualModel.getListOfQualitativeSpecies());
        this.transitionConverter = new TransitionConverter(qualModel.getListOfTransitions());
    }

    public QualModelPlugin getSbmlQualModel() {
        return this.sbmlQualModel;
    }

    public LinkedHashMap<String, ISpecies> getErodeSpecies() {
        return this.speciesConverter.getErodeSpecies();
    }

    public LinkedHashMap<String, IUpdateFunction> getUpdateFunctions() {
        return this.transitionConverter.getErodeUpdateFunctions();
    }
}
