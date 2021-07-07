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

public abstract class ModelConverter implements IModelConverter
{
    protected static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();
    protected static final String EXTENSION_NAME = "qual";

    public static IModelConverter create(@NotNull Model model) {
        return new ModelExtractor(model);
    }

    public static IModelConverter create(@NotNull IBooleanNetwork booleanNetwork) {
        return new ModelBuilder(booleanNetwork);
    }

    protected Model model;
    protected String name;

    protected IQualModelConverter qualModelConverter;


    public ModelConverter(@NotNull Model model) {
            this.model = model;
            this.name = model.getId();
    }

    public ModelConverter(IBooleanNetwork booleanNetwork) {
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
    public IQualModelConverter getQualModel() {
        return qualModelConverter;
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
