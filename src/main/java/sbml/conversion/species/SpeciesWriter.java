package sbml.conversion.species;

import it.imt.erode.crn.interfaces.ISpecies;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;

import java.util.List;

public class SpeciesWriter extends SpeciesConverter {

    private SBMLSpeciesBuilder speciesBuilder;

    public SpeciesWriter(@NotNull List<ISpecies> species) {
        super(species);
        this.sbmlSpecies = convertERODESpecies();
    }

    private ListOf<QualitativeSpecies> convertERODESpecies() {
        ListOf<QualitativeSpecies> sbmlSpecies = new ListOf<>(CONFIG.getLevel(), CONFIG.getVersion());
        for(ISpecies s : this.erodeSpecies) {
            QualitativeSpecies q = CreateQualitativeSpecies(s);
            sbmlSpecies.add(q);
        }
        return sbmlSpecies;
    }

    private QualitativeSpecies CreateQualitativeSpecies(ISpecies s) {
        QualitativeSpecies q = new QualitativeSpecies(CONFIG.getLevel(),CONFIG.getVersion());
        q.setId(s.getName());
        q.setName(s.getOriginalName());
        q.setCompartment(CONFIG.getDefaultCompartment());
        q.setMaxLevel(1);
        q.setInitialLevel(s.getInitialConcentration().intValue());
        q.setConstant(false);
        return q;
    }
}
