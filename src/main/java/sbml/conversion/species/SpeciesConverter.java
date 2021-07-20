package sbml.conversion.species;

import it.imt.erode.crn.implementations.Species;
import it.imt.erode.crn.interfaces.ISpecies;
import org.eclipse.swt.internal.C;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;
import sbml.configurations.SBMLConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class SpeciesConverter implements ISpeciesConverter {
    protected static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

    protected ListOf<QualitativeSpecies> sbmlSpecies;
    protected List<ISpecies> erodeSpecies;

    public SpeciesConverter(@NotNull ListOf<QualitativeSpecies> listOfQualitativeSpecies) {
        this.sbmlSpecies = listOfQualitativeSpecies;
    }

    public SpeciesConverter(@NotNull List<ISpecies> species) {
        this.erodeSpecies = species;
    }

    @Override
    public List<ISpecies> getErodeSpecies() {
        return this.erodeSpecies;
    }

    @Override
    public ListOf<QualitativeSpecies> getSbmlSpecies() {
        return sbmlSpecies;
    }
}
