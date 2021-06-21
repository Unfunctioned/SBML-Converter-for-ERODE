package sbml.conversion;

import it.imt.erode.crn.implementations.Species;
import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class SpeciesConverter {

    private ListOf<QualitativeSpecies> sbmlSpecies;
    private LinkedHashMap<String, ISpecies> erodeSpecies;

    public SpeciesConverter(@NotNull ListOf<QualitativeSpecies> listOfQualitativeSpecies) {
        this.sbmlSpecies = listOfQualitativeSpecies;
        this.erodeSpecies = new LinkedHashMap<>();
        this.toErodeFormat();
    }

    private LinkedHashMap<String, ISpecies> toErodeFormat() {
        int id = 0;
        for(QualitativeSpecies q : this.sbmlSpecies) {
            Species testSpecies = CreateSpecies(id,q);
            this.erodeSpecies.put(testSpecies.getName(), testSpecies);
            id++;
        }
        return this.erodeSpecies;
    }

    private Species CreateSpecies(int id, QualitativeSpecies species) {
        int initialValue = species.getInitialLevel();
        switch (initialValue) {
            case 0:
                return new Species(species.getId(),id,BigDecimal.ZERO,"false", false);
            case 1:
                return new Species(species.getId(), id, BigDecimal.ONE, "true", false);
            default:
                String startValue = String.valueOf(initialValue);
                return new Species(species.getId(),id,new BigDecimal(startValue),startValue, false);
        }
    }

    public LinkedHashMap<String, ISpecies> getErodeSpecies() {
        return this.erodeSpecies;
    }
}
