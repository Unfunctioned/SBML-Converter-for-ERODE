package sbml.conversion.model;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.Model;
import sbml.configurations.SBMLConfiguration;
import sbml.conversion.qualmodel.IQualModelConverter;
import java.util.LinkedHashMap;
import java.util.List;

abstract class ModelConverter implements IModelConverter {
    protected static final String EXTENSION_NAME = "qual";

    protected Model model;

    protected String name;

    protected IQualModelConverter qualModelConverter;


    public ModelConverter(@NotNull Model model) {
            this.model = model;
            this.name = model.getId();
    }

    public ModelConverter(@NotNull IBooleanNetwork booleanNetwork) {
        this.name = booleanNetwork.getName();

    }

    @Override
    public Model getModel() {
        return this.model;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<ISpecies> getErodeSpecies() {
        return this.qualModelConverter.getErodeSpecies();
    }

    @Override
    public LinkedHashMap<String, IUpdateFunction> getErodeUpdateFunctions() {
        return this.qualModelConverter.getUpdateFunctions();
    }
}
