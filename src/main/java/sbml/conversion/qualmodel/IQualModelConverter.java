package sbml.conversion.qualmodel;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import org.sbml.jsbml.ext.qual.QualModelPlugin;

import java.util.LinkedHashMap;
import java.util.List;

public interface IQualModelConverter {

    QualModelPlugin getSbmlQualModel();

    List<ISpecies> getErodeSpecies();

    LinkedHashMap<String, IUpdateFunction> getUpdateFunctions();

}
