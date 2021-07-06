package sbml.conversion;

import it.imt.erode.crn.interfaces.ISpecies;

import java.util.List;

public interface IConversion {
    List<ISpecies> getErodeSpecies();
}
