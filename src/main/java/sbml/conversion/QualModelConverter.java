package sbml.conversion;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import it.unipr.ce.dsg.deus.util.T;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;
import org.sbml.jsbml.ext.qual.Transition;
import sbml.configurations.SBMLConfiguration;

import java.util.LinkedHashMap;

public class QualModelConverter {
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

    private QualModelPlugin sbmlQualModel;

    private SpeciesConverter speciesConverter;
    private TransitionConverter transitionConverter;

    private IBooleanNetwork booleanNetwork;

    public QualModelConverter(@NotNull QualModelPlugin qualModel) {
        this.sbmlQualModel = qualModel;
        this.speciesConverter = new SpeciesConverter(qualModel.getListOfQualitativeSpecies());
        this.transitionConverter = new TransitionConverter(qualModel.getListOfTransitions());
    }

    public QualModelConverter(IBooleanNetwork booleanNetwork, Model model) {
        this.booleanNetwork = booleanNetwork;
        this.speciesConverter = new SpeciesConverter(booleanNetwork.getSpecies());
        this.transitionConverter = new TransitionConverter(booleanNetwork.getUpdateFunctions());

        this.sbmlQualModel = new QualModelPlugin(model);

        ListOf<QualitativeSpecies> species = speciesConverter.getSbmlSpecies();
        sbmlQualModel.setListOfQualitativeSpecies(species);

        ListOf<Transition> transitions = transitionConverter.getSbmlTransitions();
        sbmlQualModel.setListOfTransitions(transitions);
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
