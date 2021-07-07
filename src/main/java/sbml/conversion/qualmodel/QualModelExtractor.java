package sbml.conversion.qualmodel;

import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.species.SpeciesConverter;
import sbml.conversion.transitions.TransitionConverter;

public class QualModelExtractor extends QualModelConverter {

    public QualModelExtractor(@NotNull QualModelPlugin qualModelPlugin) {
        super(qualModelPlugin);
        this.speciesConverter = SpeciesConverter.create(qualModelPlugin.getListOfQualitativeSpecies());
        this.transitionConverter = TransitionConverter.create(qualModelPlugin.getListOfTransitions());
    }
}
