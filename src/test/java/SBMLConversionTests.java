import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.junit.Assert;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.SBMLConverter;

public class SBMLConversionTests {
    private SBMLDocument sbmlDocument;
    private SBMLConverter sbmlConverter;
    private GUIBooleanNetworkImporter guiBooleanNetworkImporter;

    @Given("an SBMLDocument instance with the SBML-qual extension")
    public void anSBMLDocumentInstanceWithTheSBMLQualExtension() {
        sbmlDocument = new SBMLDocument(3,1);
        Model sbmlModel = new Model();
        QualModelPlugin qualModelPlugin = new QualModelPlugin(new Model());
        sbmlModel.addExtension("qual", qualModelPlugin);
        sbmlDocument.setModel(sbmlModel);
    }

    @Given("an SBMLDocument instance without the SBML-qual extension")
    public void anSBMLDocumentInstanceWithoutTheSBMLQualExtension() {
        sbmlDocument = new SBMLDocument(3,1);
        Model sbmlModel = new Model();
        LayoutModelPlugin layoutModelPlugin = new LayoutModelPlugin(new Model());
        sbmlModel.addExtension("layout", layoutModelPlugin);
        sbmlDocument.setModel(sbmlModel);
    }

    @Given("an SBMLConverter instance created from an SBML-qual model")
    public void anSBMLConverterInstanceCreatedFromAnSBMLQualModel() {
        anSBMLDocumentInstanceWithTheSBMLQualExtension();
        attemptingToCreateAnSBMLConverterInstance();
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());
        Assert.assertEquals(SBMLConverter.class, sbmlConverter.getClass());
    }

    @Given("a boolean network in SBML format")
    public void aBooleanNetworkInSBMLFormat() {
        String path = CommonSteps.GetPath();
        try {
            sbmlDocument = (SBMLDocument) SBMLConverter.read(path);
        } catch (Exception e) {
            Assert.fail("Could not read file on path: " + path);
        }
    }

    @When("attempting to create an SBMLConverter instance")
    public void attemptingToCreateAnSBMLConverterInstance() {
        try {
            sbmlConverter = new SBMLConverter(sbmlDocument);
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @When("the model is converted")
    public void theModelIsConverted() {
        try {
            sbmlConverter.toErode();
            guiBooleanNetworkImporter = sbmlConverter.getGuiBnImporter();
        } catch (Exception e) {
            ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
            exceptionCollector.setException(e);
        }
    }

    @Then("the SBMLConverter creation succeeds")
    public void theSBMLConverterCreationSucceeds() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());
        Assert.assertNotNull(sbmlConverter.getSBMLModel());
    }

    @Then("the ERODE data structures have been created successfully")
    public void theERODEDataStructuresHaveBeenCreatedSuccessfully() {
        ExceptionCollector exceptionCollector = ExceptionCollector.getInstance();
        Assert.assertNull(exceptionCollector.getException());
        Assert.assertNotNull(guiBooleanNetworkImporter);
        Assert.assertNotNull(guiBooleanNetworkImporter.getBooleanNetwork());
        Assert.assertNotNull(sbmlConverter.getInfoImporting());
    }

    @Then("the boolean network contains a List of Species")
    public void theBooleanNetworkContainsAListOfSpecies() {
        IBooleanNetwork booleanNetwork = guiBooleanNetworkImporter.getBooleanNetwork();
        Assert.assertNotNull(booleanNetwork.getSpecies());
        Assert.assertEquals(5,booleanNetwork.getSpecies().size());
    }
}
