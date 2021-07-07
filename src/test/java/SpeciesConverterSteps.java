import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;
import sbml.conversion.document.SBMLConverter;
import sbml.conversion.species.ISpeciesConverter;
import sbml.conversion.species.SpeciesConverter;

public class SpeciesConverterSteps {
    private SBMLDocument sbmlDocument;
    private ListOf<QualitativeSpecies> qualitativeSpecies;

    private ISpeciesConverter speciesConverter;

    @Given("a valid list of qualitative species")
    public void aValidListOfQualitativeSpecies() {
        String path = CommonSteps.GetPath();
        try {
            sbmlDocument = (SBMLDocument) SBMLConverter.read(path);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Model model = sbmlDocument.getModel();
        QualModelPlugin qualModelPlugin = (QualModelPlugin) model.getExtension("qual");
        qualitativeSpecies = qualModelPlugin.getListOfQualitativeSpecies();
        Assert.assertNotNull(qualitativeSpecies);
    }

    @Given("an empty list of qualitative species")
    public void anEmptyListOfQualitativeSpecies() {
        qualitativeSpecies = new ListOf<>();
    }

    @Given("that there is no list of qualitative species")
    public void thatThereIsNoListOfQualitativeSpecies() {
        qualitativeSpecies = null;
    }

    @Given("a valid list of qualitative species with initial values ranging from {int} to {int}")
    public void aValidListOfQualitativeSpeciesWithInitialValuesRangingFromTo(int arg0, int arg1) {
        qualitativeSpecies = new ListOf<>();
        for(int i = arg0; i<=arg1; i++) {
            QualitativeSpecies q = new QualitativeSpecies(new Species("Species"+i));
            q.setInitialLevel(i);
            qualitativeSpecies.add(q);
        }
        Assert.assertEquals(qualitativeSpecies.size(),(arg1-arg0)+1);
    }

    @When("attempting to create a SpeciesConverter instance")
    public void attemptingToCreateASpeciesConverterInstance() {
        try {
            speciesConverter = SpeciesConverter.create(qualitativeSpecies);
        }
        catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @Then("the SpeciesConverter creation succeeds")
    public void theSpeciesConverterCreationSucceeds() {
        Assert.assertNotNull(speciesConverter);
        Assert.assertNotNull(speciesConverter.getErodeSpecies());
    }
}
