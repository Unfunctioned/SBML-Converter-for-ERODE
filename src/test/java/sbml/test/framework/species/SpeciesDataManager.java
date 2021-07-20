package sbml.test.framework.species;

import it.imt.erode.crn.interfaces.ISpecies;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;
import sbml.conversion.species.ISpeciesConverter;
import sbml.test.framework.TestDataManager;

import java.util.List;

public class SpeciesDataManager extends TestDataManager {
    private SBMLDocument sbmlDocument;

    private ListOf<QualitativeSpecies> qualitativeSpecies;

    private ISpeciesConverter speciesConverter;

    private List<ISpecies> species;

    public SBMLDocument getSbmlDocument() {
        return sbmlDocument;
    }

    public void setSbmlDocument(SBMLDocument sbmlDocument) {
        this.sbmlDocument = sbmlDocument;
    }

    public ListOf<QualitativeSpecies> getQualitativeSpecies() {
        if(qualitativeSpecies == null && speciesConverter != null)
            this.qualitativeSpecies = speciesConverter.getSbmlSpecies();
        return qualitativeSpecies;
    }

    public void setQualitativeSpecies(ListOf<QualitativeSpecies> qualitativeSpecies) {
        this.qualitativeSpecies = qualitativeSpecies;
    }

    public ISpeciesConverter getSpeciesConverter() {
        return speciesConverter;
    }

    public void setSpeciesConverter(ISpeciesConverter speciesConverter) {
        this.speciesConverter = speciesConverter;
    }

    public List<ISpecies> getSpecies() {
        if(species == null && speciesConverter != null)
            this.species = speciesConverter.getErodeSpecies();
        return species;
    }

    public void setSpecies(List<ISpecies> species) {
        this.species = species;
    }
}
