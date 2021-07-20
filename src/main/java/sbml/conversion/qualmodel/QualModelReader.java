package sbml.conversion.qualmodel;

import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.species.SpeciesConverter;
import sbml.conversion.species.SpeciesManager;
import sbml.conversion.transitions.TransitionConverter;
import sbml.conversion.transitions.TransitionManager;

class QualModelReader extends QualModelConverter {

    public QualModelReader(@NotNull QualModelPlugin qualModelPlugin) {
        super(qualModelPlugin);
        this.speciesConverter = SpeciesManager.create(qualModelPlugin.getListOfQualitativeSpecies());
        this.transitionConverter = TransitionManager.create(qualModelPlugin.getListOfTransitions());
    }
}
