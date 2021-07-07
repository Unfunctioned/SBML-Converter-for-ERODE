package sbml.conversion.species;

import it.imt.erode.crn.interfaces.ISpecies;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;

import java.util.List;

public interface ISpeciesConverter {

    List<ISpecies> getErodeSpecies();

    ListOf<QualitativeSpecies> getSbmlSpecies();
}
