package sbml.test.steps.qualmodel;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.NotBooleanUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import it.imt.erode.crn.implementations.Species;
import it.imt.erode.crn.interfaces.ISpecies;
import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.junit.Assert;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.qualmodel.IQualModelConverter;
import sbml.conversion.qualmodel.QualModelConverter;
import sbml.test.framework.TestDataManager;
import sbml.test.framework.qualmodel.QualModelManager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static sbml.test.framework.TestDataManager.Type;

public class QualModelConversionSteps {

    private QualModelManager qualModelManager;

    @Given("a QualModelManager has been initialized")
    public void aQualModelManagerHasBeenInitialized() {
        TestDataManager.setInstance(Type.QUAL);
        qualModelManager = (QualModelManager) TestDataManager.getInstance();
    }

    @Given("a valid QualModelPlugin")
    public void aValidQualModelPlugin() {
        QualModelPlugin qualModelPlugin = new QualModelPlugin(new Model(3,1));
        qualModelManager.setQualModelPlugin(qualModelPlugin);
    }

    @Given("that there is no QualModelPlugin instance")
    public void thatThereIsNoQualModelPluginInstance() {
        qualModelManager.setQualModelPlugin(null);
    }

    @Given("a valid boolean network")
    public void aValidBooleanNetwork() {
        GUIBooleanNetworkImporter importer = new GUIBooleanNetworkImporter(null, null, null);
        try {
            importer.importBooleanNetwork(true,true, true,
                    "TestNetwork",
                    new ArrayList<ArrayList<String>>(),
                    new LinkedHashMap<String, IUpdateFunction>(),
                    new ArrayList<ArrayList<String>>(),
                    null);
        } catch (IOException e) {
            Assert.fail("Could not create boolean network");
        }
        IBooleanNetwork bn = importer.getBooleanNetwork();
        ISpecies s1 = new Species("Spwcies1", 0, BigDecimal.ZERO, "false");
        ISpecies s2 = new Species("Spwcies1", 1, BigDecimal.ZERO, "false");
        bn.addSpecies(s1);
        bn.addSpecies(s2);
        LinkedHashMap<String, IUpdateFunction> map = new LinkedHashMap<>();
        IUpdateFunction updateFunction1 = new NotBooleanUpdateFunction(
                new ReferenceToNodeUpdateFunction(s2.getName()));
        IUpdateFunction updateFunction2 = new NotBooleanUpdateFunction(
                new ReferenceToNodeUpdateFunction(s1.getName()));
        map.put(s1.getName(), updateFunction1);
        map.put(s2.getName(), updateFunction2);
        bn.setAllUpdateFunctions(map);
        qualModelManager.setBooleanNetwork(bn);
    }

    @Given("an SBML model to be populated with data")
    public void anSBMLModelToBePopulatedWithData() {
        SBMLDocument sbmlDocument = new SBMLDocument(3,1);
        sbmlDocument.setModel(new Model(3,1));
        qualModelManager.setModel(sbmlDocument.getModel());
    }

    @When("the QualModelConverter is created for the ERODE conversion")
    public void theQualModelConverterIsCreatedForTheERODEConversion() {
        try {
            QualModelPlugin qualModelPlugin = qualModelManager.getQualModelPlugin();
            IQualModelConverter qualModelConverter = QualModelConverter.create(qualModelPlugin);
            qualModelManager.setQualModelConverter(qualModelConverter);
        } catch (Exception e) {
            qualModelManager.setException(e);
        }
    }

    @When("the QualModelConverter is created for the SBML conversion")
    public void theQualModelConverterIsCreatedForTheSBMLConversion() {
        try {
            IBooleanNetwork booleanNetwork = qualModelManager.getBooleanNetwork();
            Model model = qualModelManager.getModel();
            IQualModelConverter qualModelConverter = QualModelConverter.create(booleanNetwork, model);
            qualModelManager.setQualModelConverter(qualModelConverter);
        } catch (Exception e) {
            qualModelManager.setException(e);
        }
    }

    @Then("the QualModelConverter creation succeeds")
    public void theQualModelConverterCreationSucceeds() {
        Assert.assertNull(qualModelManager.getException());
        Assert.assertNotNull(qualModelManager.getQualModelConverter());
    }

    @Then("a list of species converted to ERODE format is available")
    public void aListOfSpeciesConvertedToERODEFormatIsAvailable() {
        IQualModelConverter qualModelConverter = qualModelManager.getQualModelConverter();
        Assert.assertNotNull(qualModelConverter.getErodeSpecies());
    }

    @Then("a map of update functions converted to ERODE format is available")
    public void aMapOfUpdateFunctionsConvertedToERODEFormatIsAvailable() {
        IQualModelConverter qualModelConverter = qualModelManager.getQualModelConverter();
        Assert.assertNotNull(qualModelConverter.getUpdateFunctions());
    }

    @Then("a QualModel plugin representing the boolean network is available")
    public void aQualModelPluginRepresentingTheBooleanNetworkIsAvailable() {
        Assert.assertNull(qualModelManager.getException());
        Assert.assertNotNull(qualModelManager.getQualModelPlugin());
    }
}
