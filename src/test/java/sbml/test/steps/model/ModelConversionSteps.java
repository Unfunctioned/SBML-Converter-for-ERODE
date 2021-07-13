package sbml.test.steps.model;

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
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.model.IModelConverter;
import sbml.conversion.model.ModelConverter;
import sbml.test.framework.TestDataManager;
import sbml.test.framework.model.ModelManager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static sbml.test.framework.TestDataManager.Type;

public class ModelConversionSteps {
    private ModelManager modelManager;

    @Given("a new ModelManager instance")
    public void aNewModelManagerInstance() {
        TestDataManager.setInstance(Type.MODEL);
        modelManager = (ModelManager) TestDataManager.getInstance();
    }

    @Given("a valid SBML-qual model")
    public void aValidSBMLQualModel() {
        modelManager.setModel(new Model(3,1));
        QualModelPlugin qualModelPlugin = new QualModelPlugin(modelManager.getModel());
        Model model = modelManager.getModel();
        model.addExtension("qual", qualModelPlugin);
    }

    @Given("that there is no SBML Model")
    public void thatThereIsNoSBMLModel() {
        modelManager.setModel(null);
    }

    @Given("a non-SBML-qual model")
    public void aNonSBMLQualModel() {
        modelManager.setModel(new Model(3,1));
    }

    @Given("there is a valid boolean network available to the model")
    public void thereIsAValidBooleanNetworkAvailableToTheModel() {
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
        modelManager.setBooleanNetwork(bn);
    }

    @When("attempting to create a ModelConverter instance for ERODE format")
    public void attemptingToCreateAModelConverterInstanceForERODEFormat() {
        try {
            Model model = modelManager.getModel();
            IModelConverter modelConverter = ModelConverter.create(model);
            modelManager.setModelConverter(modelConverter);
        } catch (Exception e) {
            modelManager.setException(e);
        }
    }

    @When("attempting to create a ModelConverter instance for SBML format")
    public void attemptingToCreateAModelConverterInstanceForSBMLFormat() {
        try {
            IBooleanNetwork booleanNetwork = modelManager.getBooleanNetwork();
            IModelConverter modelConverter = ModelConverter.create(booleanNetwork);
            modelManager.setModelConverter(modelConverter);
        } catch (Exception e) {
            modelManager.setException(e);
        }
    }

    @Then("the ModelConverter creation succeeds")
    public void theModelConverterCreationSucceeds() {
        Assert.assertNull(modelManager.getException());
        IModelConverter modelConverter = modelManager.getModelConverter();
        Assert.assertNotNull(modelConverter);
        Assert.assertNotNull(modelConverter.getName());
    }

    @Then("a list of species converted to ERODE format is available through the ModelConverter")
    public void aListOfSpeciesConvertedToERODEFormatIsAvailableThroughTheModelConverter() {
        IModelConverter modelConverter = modelManager.getModelConverter();
        Assert.assertNotNull(modelConverter.getErodeSpecies());
    }

    @Then("a map of update functions converted to ERODE format is available through the ModelConverter")
    public void aMapOfUpdateFunctionsConvertedToERODEFormatIsAvailableThroughTheModelConverter() {
        IModelConverter modelConverter = modelManager.getModelConverter();
        Assert.assertNotNull(modelConverter.getErodeUpdateFunctions());
    }

    @Then("a Model representing the boolean network is available through the ModelConverter")
    public void aModelRepresentingTheBooleanNetworkIsAvailableThroughTheModelConverter() {
        IModelConverter modelConverter = modelManager.getModelConverter();
        Assert.assertNotNull(modelConverter.getModel());
    }
}
