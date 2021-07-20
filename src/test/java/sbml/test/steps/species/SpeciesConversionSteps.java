package sbml.test.steps.species;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.crn.implementations.Species;
import it.imt.erode.crn.interfaces.ISpecies;
import org.junit.Assert;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;
import sbml.conversion.document.SBMLManager;
import sbml.conversion.species.ISpeciesConverter;
import sbml.conversion.species.SpeciesManager;
import sbml.test.framework.TestDataManager;
import sbml.test.framework.species.SpeciesDataManager;
import sbml.test.steps.CommonSteps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static sbml.test.framework.TestDataManager.Type;

public class SpeciesConversionSteps {

    private SpeciesDataManager speciesDataManager;

    @Given("the SpeciesManger has been initialised")
    public void theSpeciesMangerHasBeenInitialised() {
        TestDataManager.setInstance(Type.SPECIES);
        speciesDataManager = (SpeciesDataManager) TestDataManager.getInstance();
    }

    @Given("a valid list of qualitative species")
    public void aValidListOfQualitativeSpecies() {
        String path = CommonSteps.GetPath();
        try {
            speciesDataManager.setSbmlDocument((SBMLDocument) SBMLManager.read(path));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        SBMLDocument sbmlDocument = speciesDataManager.getSbmlDocument();
        Model model = sbmlDocument.getModel();
        QualModelPlugin qualModelPlugin = (QualModelPlugin) model.getExtension("qual");
        ListOf<QualitativeSpecies> qualitativeSpecies = qualModelPlugin.getListOfQualitativeSpecies();
        speciesDataManager.setQualitativeSpecies(qualitativeSpecies);
        Assert.assertNotNull(qualitativeSpecies);
    }

    @Given("an empty list of qualitative species")
    public void anEmptyListOfQualitativeSpecies() {
        speciesDataManager.setQualitativeSpecies(new ListOf<>(3,1));
    }

    @Given("that there is no list of qualitative species")
    public void thatThereIsNoListOfQualitativeSpecies() {
        speciesDataManager.setQualitativeSpecies(null);
    }

    @Given("a valid list of qualitative species with initial values ranging from {int} to {int}")
    public void aValidListOfQualitativeSpeciesWithInitialValuesRangingFromTo(int arg0, int arg1) {
        speciesDataManager.setQualitativeSpecies(new ListOf<>(3,1));
        ListOf<QualitativeSpecies> species = speciesDataManager.getQualitativeSpecies();
        for(int i = arg0; i<=arg1; i++) {
            QualitativeSpecies q = new QualitativeSpecies(new org.sbml.jsbml.Species("Species"+i));
            q.setInitialLevel(i);
            species.add(q);
        }
        Assert.assertEquals(species.size(),(arg1-arg0)+1);
    }

    @Given("a list of ERODE species")
    public void aListOfERODESpecies() {
        List<ISpecies> species = new ArrayList<>();
        Species species1 = new Species("TestSpecies1", 0, BigDecimal.ZERO, "0");
        Species species2 = new Species("TestSpecies2", 1, BigDecimal.ZERO, "0");
        species.add(species1);
        species.add(species2);
        speciesDataManager.setSpecies(species);
    }

    @When("attempting to create a SpeciesConverter instance for ERODE format")
    public void attemptingToCreateASpeciesConverterInstanceForERODEFormat() {
        try {
            ListOf<QualitativeSpecies> species = speciesDataManager.getQualitativeSpecies();
            ISpeciesConverter speciesConverter = SpeciesManager.create(species);
            speciesDataManager.setSpeciesConverter(speciesConverter);
        }
        catch (Exception e) {
            speciesDataManager.setException(e);
        }
    }

    @When("attempting to create a SpeciesConverter instance for SBML format")
    public void attemptingToCreateASpeciesConverterInstanceForSBMLFormat() {
        try {
            List<ISpecies> species = speciesDataManager.getSpecies();
            ISpeciesConverter speciesConverter = SpeciesManager.create(species);
            speciesDataManager.setSpeciesConverter(speciesConverter);
        }
        catch (Exception e) {
            speciesDataManager.setException(e);
        }
    }

    @Then("the SpeciesConverter creation succeeds")
    public void theSpeciesConverterCreationSucceeds() {
        ISpeciesConverter speciesConverter = speciesDataManager.getSpeciesConverter();
        Assert.assertNotNull(speciesConverter);
        Assert.assertNotNull(speciesConverter.getErodeSpecies());
    }

    @Then("a List of ERODE species is available")
    public void aListOfERODESpeciesIsAvailable() {
        Assert.assertNotNull(speciesDataManager.getSpecies());
    }

    @Then("a list of qualitative species is available")
    public void aListOfQualitativeSpeciesIsAvailable() {
        Assert.assertNotNull(speciesDataManager.getQualitativeSpecies());
    }
}
