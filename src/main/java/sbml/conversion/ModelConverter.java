package sbml.conversion;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.configurations.SBMLConfiguration;

import java.util.LinkedHashMap;

public class ModelConverter implements IConversion
{
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();
    private static final String QUAL = "qual";

    private String name;
    private QualModelConverter qualModelConverter;

    private Model model;

    public ModelConverter(@NotNull Model model) {
            this.model = model;
            this.name = model.getId(); //The actual model name is stored in the parameter id
            try {
                this.qualModelConverter = new QualModelConverter((QualModelPlugin) model.getExtension("qual"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new IllegalArgumentException("Invalid input, the SBML-model is not an SBML-qual model");

            }
    }

    public ModelConverter(IBooleanNetwork booleanNetwork) {
        System.out.println("Packaging qual-model");
        this.name = booleanNetwork.getName();
        this.model = new Model(CONFIG.getLevel(),CONFIG.getVersion());
        this.qualModelConverter = new QualModelConverter(booleanNetwork, model);
        System.out.println("Initialized qual model conversion");
        QualModelPlugin qualModelPlugin = qualModelConverter.getSbmlQualModel();
        qualModelPlugin.setParent(model);
        System.out.println("Test");
        model.addExtension(QUAL,qualModelPlugin);
        System.out.println("Added model to main SBML model");
    }

    public String getName() {
        return name;
    }

    public QualModelConverter getQualModel() {
        return qualModelConverter;
    }

    public LinkedHashMap<String, ISpecies> getErodeSpecies() {
        return this.qualModelConverter.getErodeSpecies();
    }

    public LinkedHashMap<String, IUpdateFunction> getErodeUpdateFunctions() {
        return this.qualModelConverter.getUpdateFunctions();
    }

    public Model getModel() {
        return this.model;
    }
}
