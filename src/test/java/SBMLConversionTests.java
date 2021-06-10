import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.junit.Assert;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.SBMLConverter;
import sbml.models.SBMLModel;

public class SBMLConversionTests {
    private SBMLDocument sbmlDocument;
    private SBMLConverter sbmlConverter;
    private Exception exception;
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
        Assert.assertNull(exception);
        Assert.assertEquals(SBMLConverter.class, sbmlConverter.getClass());
    }

    @When("attempting to create an SBMLConverter instance")
    public void attemptingToCreateAnSBMLConverterInstance() {
        try {
            sbmlConverter = new SBMLConverter(sbmlDocument);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("the model is converted")
    public void theModelIsConverted() {
        try {
            sbmlConverter.convert();
            guiBooleanNetworkImporter = sbmlConverter.getGuiBnImporter();
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the creation succeeds")
    public void theCreationSucceeds() {
        Assert.assertNull(exception);
        Assert.assertNotNull(sbmlConverter.getSBMLModel());
    }

    @Then("an exception with message is {string} thrown")
    public void anExceptionWithMessageIsThrown(String arg0) {
        Assert.assertNotNull(exception);
        Assert.assertEquals(arg0, exception.getMessage());
    }

    @Then("the ERODE data structures have been created successfully")
    public void theERODEDataStructuresHaveBeenCreatedSuccessfully() {
        Assert.assertNull(exception);
        Assert.assertNotNull(guiBooleanNetworkImporter);
        Assert.assertNotNull(guiBooleanNetworkImporter.getBooleanNetwork());
        Assert.assertNotNull(sbmlConverter.getInfoImporting());
    }
}
