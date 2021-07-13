package sbml.conversion.model;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import org.sbml.jsbml.Model;
import sbml.conversion.qualmodel.IQualModelConverter;

import java.util.LinkedHashMap;
import java.util.List;

public interface IModelConverter {

    Model getModel();

    String getName();

    List<ISpecies> getErodeSpecies();

    LinkedHashMap<String, IUpdateFunction> getErodeUpdateFunctions();
}
