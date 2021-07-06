package sbml.conversion;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.configurations.SBMLConfiguration;

import java.util.LinkedHashMap;
import java.util.List;

public class ModelConverter implements IConversion
{
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();
    private static final String EXTENSION_NAME = "qual";

    private String name;
    private QualModelConverter qualModelConverter;

    private Model model;

    public ModelConverter(@NotNull Model model) {
            this.model = model;
            this.name = model.getId();
            this.qualModelConverter = new QualModelConverter(this.tryGetQualModel());
    }

    private QualModelPlugin tryGetQualModel() {
        try {
            return (QualModelPlugin) model.getExtension("qual");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid input, the SBML-model is not an SBML-qual model");
        }
    }

    public ModelConverter(IBooleanNetwork booleanNetwork) {
        this.name = booleanNetwork.getName();
        this.model = new Model(CONFIG.getLevel(),CONFIG.getVersion());
        this.qualModelConverter = new QualModelConverter(booleanNetwork, model);
        this.packageSBML();
    }

    private void packageSBML() {
        QualModelPlugin qualModelPlugin = qualModelConverter.getSbmlQualModel();
        model.addExtension(EXTENSION_NAME,qualModelPlugin);
    }


    public String getName() {
        return name;
    }

    public QualModelConverter getQualModel() {
        return qualModelConverter;
    }

    public List<ISpecies> getErodeSpecies() {
        return this.qualModelConverter.getErodeSpecies();
    }

    public LinkedHashMap<String, IUpdateFunction> getErodeUpdateFunctions() {
        return this.qualModelConverter.getUpdateFunctions();
    }

    public Model getModel() {
        return this.model;
    }
}
