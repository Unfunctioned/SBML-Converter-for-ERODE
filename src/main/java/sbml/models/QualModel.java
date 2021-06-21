package sbml.models;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.SpeciesConverter;
import sbml.conversion.UpdateFunctionConverter;

import java.util.LinkedHashMap;

public class QualModel {

    private QualModelPlugin sbmlQualModel;
    private SpeciesConverter speciesConverter;
    private UpdateFunctionConverter updateFunctionConverter;

    public QualModel(@NotNull QualModelPlugin qualModel) {
        this.sbmlQualModel = qualModel;
        this.speciesConverter = new SpeciesConverter(qualModel.getListOfQualitativeSpecies());
        this.updateFunctionConverter = new UpdateFunctionConverter(qualModel.getListOfTransitions());
    }

    public QualModelPlugin getSbmlQualModel() {
        return this.sbmlQualModel;
    }

    public LinkedHashMap<String, ISpecies> getErodeSpecies() {
        return this.speciesConverter.getErodeSpecies();
    }

    public LinkedHashMap<String, IUpdateFunction> getUpdateFunctions() {
        return this.updateFunctionConverter.getErodeUpdateFunctions();
    }
}
