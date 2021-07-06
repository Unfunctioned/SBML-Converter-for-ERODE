package sbml.conversion;

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

public class SpeciesConverter {
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

    private ListOf<QualitativeSpecies> sbmlSpecies;
    private List<ISpecies> erodeSpecies;

    public SpeciesConverter(@NotNull ListOf<QualitativeSpecies> listOfQualitativeSpecies) {
        this.sbmlSpecies = listOfQualitativeSpecies;
        this.erodeSpecies = convertSBMLSpecies();
    }

    private List<ISpecies> convertSBMLSpecies() {
        List<ISpecies> erodeSpecies = new ArrayList<>();
        int id = 0;
        for(QualitativeSpecies q : this.sbmlSpecies) {
            erodeSpecies.add(CreateSpecies(id,q));
            id++;
        }
        return erodeSpecies;
    }

    private Species CreateSpecies(int id, QualitativeSpecies species) {
        int initialValue = species.getInitialLevel();
        switch (initialValue) {
            case 0:
                return new Species(species.getId(),id,BigDecimal.ZERO,"false", false);
            case 1:
                return new Species(species.getId(), id, BigDecimal.ONE, "true", false);
            default:
                throw new IllegalArgumentException("The value of the given species is outside the Boolean Domain");
                //Example code for multi-valued speices:
                /*String startValue = String.valueOf(initialValue);
                return new Species(species.getId(),id,new BigDecimal(startValue),startValue, false);*/
        }
    }





    public SpeciesConverter(@NotNull List<ISpecies> species) {
        this.erodeSpecies = species;
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

    public List<ISpecies> getErodeSpecies() {
        return this.erodeSpecies;
    }

    public ListOf<QualitativeSpecies> getSbmlSpecies() {
        return sbmlSpecies;
    }
}
