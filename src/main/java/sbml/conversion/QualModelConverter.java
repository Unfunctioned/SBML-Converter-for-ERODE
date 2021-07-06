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
import java.util.List;

public class QualModelConverter {

    private QualModelPlugin sbmlQualModel;

    private SpeciesConverter speciesConverter;
    private TransitionConverter transitionConverter;

    public QualModelConverter(@NotNull QualModelPlugin qualModelPlugin) {
        this.sbmlQualModel = qualModelPlugin;
        this.speciesConverter = new SpeciesConverter(qualModelPlugin.getListOfQualitativeSpecies());
        this.transitionConverter = new TransitionConverter(qualModelPlugin.getListOfTransitions());
    }

    public QualModelConverter(@NotNull IBooleanNetwork booleanNetwork, Model model) {
        this.speciesConverter = new SpeciesConverter(booleanNetwork.getSpecies());
        this.transitionConverter = new TransitionConverter(booleanNetwork.getUpdateFunctions());
        this.sbmlQualModel = new QualModelPlugin(model);
        this.buildSBMLQualModel(speciesConverter.getSbmlSpecies(),
                transitionConverter.getSbmlTransitions());
    }

    private void buildSBMLQualModel(ListOf<QualitativeSpecies> sbmlSpecies,
                                    ListOf<Transition> sbmlTransitions) {
        sbmlQualModel.setListOfQualitativeSpecies(sbmlSpecies);
        sbmlQualModel.setListOfTransitions(sbmlTransitions);
    }

    public QualModelPlugin getSbmlQualModel() {
        return this.sbmlQualModel;
    }

    public List<ISpecies> getErodeSpecies() {
        return this.speciesConverter.getErodeSpecies();
    }

    public LinkedHashMap<String, IUpdateFunction> getUpdateFunctions() {
        return this.transitionConverter.getErodeUpdateFunctions();
    }
}
