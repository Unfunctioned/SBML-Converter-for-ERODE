package sbml.test.steps.document;

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
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.document.ISBMLConverter;
import sbml.conversion.document.SBMLConverter;
import sbml.test.framework.ExceptionCollector;
import sbml.test.framework.TestDataManager;
import sbml.test.framework.document.DocumentManager;
import sbml.test.steps.CommonSteps;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static sbml.test.framework.TestDataManager.Type;

public class SBMLDocumentConversion {

    private DocumentManager documentManager;

    @Given("a DocumentManager has been initialised")
    public void aDocumentManagerHasBeenInitialised() {
        TestDataManager.setInstance(Type.DOCUMENT);
        documentManager = (DocumentManager) TestDataManager.getInstance();
    }

    @Given("an SBMLDocument instance with the SBML-qual extension")
    public void anSBMLDocumentInstanceWithTheSBMLQualExtension() {
        documentManager.setSbmlDocument(new SBMLDocument(3,1));
        Model sbmlModel = new Model();
        QualModelPlugin qualModelPlugin = new QualModelPlugin(new Model());
        sbmlModel.addExtension("qual", qualModelPlugin);
        SBMLDocument sbmlDocument = documentManager.getSbmlDocument();
        sbmlDocument.setModel(sbmlModel);
    }

    @Given("an SBMLDocument instance without the SBML-qual extension")
    public void anSBMLDocumentInstanceWithoutTheSBMLQualExtension() {
        SBMLDocument sbmlDocument = new SBMLDocument(3,1);
        Model sbmlModel = new Model();
        LayoutModelPlugin layoutModelPlugin = new LayoutModelPlugin(new Model());
        sbmlModel.addExtension("layout", layoutModelPlugin);
        sbmlDocument.setModel(sbmlModel);
        documentManager.setSbmlDocument(sbmlDocument);
    }

    @Given("a boolean network in SBML format")
    public void aBooleanNetworkInSBMLFormat() {
        String path = CommonSteps.GetPath();
        try {
            SBMLDocument sbmlDocument = (SBMLDocument) SBMLConverter.read(path);
            documentManager.setSbmlDocument(sbmlDocument);
        } catch (Exception e) {
            Assert.fail("Could not read file on path: " + path);
        }
    }

    @Given("a boolean network in SBML format to convert")
    public void aBooleanNetworkInSBMLFormatToConvert() {
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
        documentManager.setBooleanNetwork(bn);
    }

    @When("attempting to create a SBMLDocumentConverter instance for ERODE format")
    public void attemptingToCreateASBMLDocumentConverterInstanceForERODEFormat() {
        try {
            SBMLDocument sbmlDocument = documentManager.getSbmlDocument();
            ISBMLConverter sbmlConverter = SBMLConverter.create(sbmlDocument);
            documentManager.setSbmlConverter(sbmlConverter);
        } catch (Exception e) {
            documentManager.setException(e);
        }
    }

    @When("attempting to create a SBMLDocumentConverter instance for SBML format")
    public void attemptingToCreateASBMLDocumentConverterInstanceForSBMLFormat() {
        try {
            IBooleanNetwork booleanNetwork = documentManager.getBooleanNetwork();
            ISBMLConverter sbmlConverter = SBMLConverter.create(booleanNetwork);
            documentManager.setSbmlConverter(sbmlConverter);
        } catch (Exception e) {
            documentManager.setException(e);
        }
    }

    @Then("the SBMLDocumentConverter creation succeeds")
    public void theSBMLDocumentConverterCreationSucceeds() {
        Assert.assertNull(documentManager.getException());
        ISBMLConverter sbmlConverter = documentManager.getSbmlConverter();
        Assert.assertNotNull(sbmlConverter.getSBMLModel());
    }

    @Then("the boolean network contains a List of Species")
    public void theBooleanNetworkContainsAListOfSpecies() {
        GUIBooleanNetworkImporter guiBooleanNetworkImporter = documentManager.getGuiBooleanNetworkImporter();
        IBooleanNetwork booleanNetwork = guiBooleanNetworkImporter.getBooleanNetwork();
        Assert.assertNotNull(booleanNetwork.getSpecies());
        Assert.assertEquals(5,booleanNetwork.getSpecies().size());
    }

    @Then("the complete ERODE model structure is available through the SBMLConverter")
    public void theCompleteERODEModelStructureIsAvailableThroughTheSBMLConverter() {
        ISBMLConverter sbmlConverter = documentManager.getSbmlConverter();
        GUIBooleanNetworkImporter guiBooleanNetworkImporter = sbmlConverter.getGuiBnImporter();
        Assert.assertNotNull(guiBooleanNetworkImporter);
        Assert.assertNotNull(guiBooleanNetworkImporter.getBooleanNetwork());
        Assert.assertNotNull(sbmlConverter.getInfoImporting());

    }

    @Then("an SBMLDocument representing the boolean network is available through the SBMLDocument converter")
    public void anSBMLDocumentRepresentingTheBooleanNetworkIsAvailableThroughTheSBMLDocumentConverter() {
        ISBMLConverter sbmlConverter = documentManager.getSbmlConverter();
        Assert.assertNotNull(sbmlConverter.getSbmlDocument());
    }
}
