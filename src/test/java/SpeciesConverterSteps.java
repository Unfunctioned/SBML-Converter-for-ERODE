import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.crn.interfaces.ISpecies;
import org.junit.Assert;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;
import sbml.conversion.SBMLConverter;
import sbml.conversion.SpeciesConverter;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class SpeciesConverterSteps {
    private SBMLDocument sbmlDocument;
    private ListOf<QualitativeSpecies> qualitativeSpecies;

    private SpeciesConverter speciesConverter;

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
            speciesConverter = new SpeciesConverter(qualitativeSpecies);
        }
        catch (Exception e) {
            ExceptionCollector.setExceptionInstance(e);
        }
    }

    @Then("the SpeciesConverter creation succeeds")
    public void theSpeciesConverterCreationSucceeds() {
        Assert.assertNotNull(speciesConverter);
        Assert.assertNotNull(speciesConverter.getErodeSpecies());
    }

    @Then("the SpeciesConverter contains a list of ERODE-Species with initial values ranging from {int} to {int}")
    public void theSpeciesConverterContainsAListOfERODESpeciesWithInitialValuesRangingFromTo(int arg0, int arg1) {
        boolean[] confirmedInitialValues = new boolean[(arg1-arg0)+1];
        LinkedHashMap<String, ISpecies> erodeSpecies = speciesConverter.getErodeSpecies();
        for (QualitativeSpecies q : qualitativeSpecies) {
            try {
                ISpecies s = erodeSpecies.get(q.getId());
                Assert.assertEquals(q.getId(),s.getName());
                Assert.assertEquals(new BigDecimal(String.valueOf(q.getInitialLevel())),s.getInitialConcentration());
                int initialValue = s.getInitialConcentration().intValue();
                if(!confirmedInitialValues[initialValue-arg0])
                    confirmedInitialValues[initialValue-arg0] = true;
            } catch (Exception e) {
                Assert.fail();
            }
        }
        for (boolean b : confirmedInitialValues) {
            if(!b)
                Assert.fail();
        }
    }
}
