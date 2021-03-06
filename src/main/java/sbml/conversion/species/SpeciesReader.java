package sbml.conversion.species;

import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;

import java.util.ArrayList;
import java.util.List;

class SpeciesReader extends SpeciesConverter {

    private ErodeSpeciesBuilder erodeSpeciesBuilder;

    public SpeciesReader(@NotNull ListOf<QualitativeSpecies> listOfQualitativeSpecies) {
        super(listOfQualitativeSpecies);
        this.erodeSpeciesBuilder = new ErodeSpeciesBuilder();
        this.erodeSpecies = convertSBMLSpecies();
    }

    private List<ISpecies> convertSBMLSpecies() {
        List<ISpecies> erodeSpecies = new ArrayList<>();
        int id = 0;
        for(QualitativeSpecies q : this.sbmlSpecies) {
            ISpecies s;
            if(!q.isSetInitialLevel())
                s = erodeSpeciesBuilder.createSpecies(id, q.getId());
            else
                s = erodeSpeciesBuilder.createSpecies(id, q.getId(), q.getInitialLevel());
            erodeSpecies.add(s);
            id++;
        }
        return erodeSpecies;
    }
}
