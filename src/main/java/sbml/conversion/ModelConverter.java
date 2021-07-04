package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;

import java.util.LinkedHashMap;

public class ModelConverter implements IConversion
{

    private String name;
    private QualModelConverter qualModelConverter;

    public ModelConverter(@NotNull Model model) {
            this.name = model.getId(); //The actual model name is stored in the parameter id
            try {
                this.qualModelConverter = new QualModelConverter((QualModelPlugin) model.getExtension("qual"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new IllegalArgumentException("Invalid input, the SBML-model is not an SBML-qual model");

            }
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
}
