package sbml.conversion;

import it.imt.erode.crn.interfaces.ISpecies;

import java.util.LinkedHashMap;

public interface IConversion {
    LinkedHashMap<String, ISpecies> getErodeSpecies();
}
