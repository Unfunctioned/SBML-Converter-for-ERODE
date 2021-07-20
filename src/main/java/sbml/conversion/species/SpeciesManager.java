package sbml.conversion.species;

import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;

import java.util.List;

public class SpeciesManager {
    public static ISpeciesConverter create(@NotNull ListOf<QualitativeSpecies> qualitativeSpecies) {
        return new SpeciesReader(qualitativeSpecies);
    }

    public static ISpeciesConverter create(@NotNull List<ISpecies> species) {
        return new SpeciesWriter(species);
    }
}
