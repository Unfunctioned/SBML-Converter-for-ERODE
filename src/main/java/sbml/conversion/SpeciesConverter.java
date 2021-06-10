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
            //Species s = new Species(string name, int id, BigDecimal initialConcentration, string initialConcentrationExpression);
            Species testSpecies = new Species(q.getId(), id, BigDecimal.ONE,"true", false);
            this.erodeSpecies.put(testSpecies.getName(), testSpecies);
            id++;
        }
        return this.erodeSpecies;
    }

    public LinkedHashMap<String, ISpecies> getErodeSpecies() {
        return this.erodeSpecies;
    }
}
