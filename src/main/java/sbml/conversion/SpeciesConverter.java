package sbml.conversion;

import it.imt.erode.crn.implementations.Species;
import it.imt.erode.crn.interfaces.ISpecies;
import org.eclipse.swt.internal.C;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;
import sbml.configurations.SBMLConfiguration;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

public class SpeciesConverter {
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

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
            Species species = CreateSpecies(id,q);
            this.erodeSpecies.put(species.getName(), species);
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





    public SpeciesConverter(@NotNull List<ISpecies> species) {
        sbmlSpecies = new ListOf<>(CONFIG.getLevel(), CONFIG.getVersion());
        this.toSBMLFormat(species);
    }

    private void toSBMLFormat(List<ISpecies> species) {
        for(ISpecies s : species) {
            QualitativeSpecies q = CreateQualitativeSpecies(s);
             sbmlSpecies.add(q);
        }
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

    public LinkedHashMap<String, ISpecies> getErodeSpecies() {
        return this.erodeSpecies;
    }

    public ListOf<QualitativeSpecies> getSbmlSpecies() {
        return sbmlSpecies;
    }
}
