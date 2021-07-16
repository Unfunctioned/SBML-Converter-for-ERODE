package sbml.conversion.qualmodel;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.species.ISpeciesConverter;
import sbml.conversion.transitions.ITransitionConverter;

import java.util.LinkedHashMap;
import java.util.List;

public abstract class QualModelConverter implements IQualModelConverter {

    public static IQualModelConverter create(@NotNull QualModelPlugin qualModelPlugin) {
        return new QualModelReader(qualModelPlugin);
    }

    public static IQualModelConverter create(@NotNull IBooleanNetwork booleanNetwork, Model model) {
        return new QualModelWriter(booleanNetwork, model);
    }

    protected QualModelPlugin sbmlQualModel;

    protected ISpeciesConverter speciesConverter;
    protected ITransitionConverter transitionConverter;

    public QualModelConverter() { }

    public QualModelConverter(@NotNull QualModelPlugin qualModelPlugin) {
        this.sbmlQualModel = qualModelPlugin;
    }

    @Override
    public QualModelPlugin getSbmlQualModel() {
        return this.sbmlQualModel;
    }

    @Override
    public List<ISpecies> getErodeSpecies() {
        return this.speciesConverter.getErodeSpecies();
    }

    @Override
    public LinkedHashMap<String, IUpdateFunction> getUpdateFunctions() {
        return this.transitionConverter.getErodeUpdateFunctions();
    }
}
