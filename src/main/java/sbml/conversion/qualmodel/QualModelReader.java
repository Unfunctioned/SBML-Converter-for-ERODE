package sbml.conversion.qualmodel;

import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.species.SpeciesConverter;
import sbml.conversion.transitions.TransitionConverter;

class QualModelReader extends QualModelConverter {

    public QualModelReader(@NotNull QualModelPlugin qualModelPlugin) {
        super(qualModelPlugin);
        this.speciesConverter = SpeciesConverter.create(qualModelPlugin.getListOfQualitativeSpecies());
        this.transitionConverter = TransitionConverter.create(qualModelPlugin.getListOfTransitions());
    }
}
