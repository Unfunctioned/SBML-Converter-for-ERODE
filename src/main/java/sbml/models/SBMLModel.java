package sbml.models;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.IConversion;

import java.util.LinkedHashMap;

public class SBMLModel implements IConversion
{

    private String name;
    private QualModel qualModel;

    public SBMLModel(@NotNull Model model) {
            this.name = model.getId(); //The actual model name is stored in the parameter id
            try {
                this.qualModel = new QualModel((QualModelPlugin) model.getExtension("qual"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new IllegalArgumentException("Invalid input, the SBML-model is not an SBML-qual model");

            }
    }

    public String getName() {
        return name;
    }

    public QualModel getQualModel() {
        return qualModel;
    }

    public LinkedHashMap<String, ISpecies> getErodeSpecies() {
        return this.qualModel.getErodeSpecies();
    }

    public LinkedHashMap<String, IUpdateFunction> getErodeUpdateFunctions() {
        return this.qualModel.getUpdateFunctions();
    }
}
