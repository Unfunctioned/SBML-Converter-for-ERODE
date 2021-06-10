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
        Assert.assertNull(ExceptionCollector.getExceptionInstance());
        Assert.assertEquals(SBMLConverter.class, sbmlConverter.getClass());
    }

    @When("attempting to create an SBMLConverter instance")
    public void attemptingToCreateAnSBMLConverterInstance() {
        try {
            sbmlConverter = new SBMLConverter(sbmlDocument);
        } catch (Exception e) {
            ExceptionCollector.setExceptionInstance(e);
        }
    }

    @When("the model is converted")
    public void theModelIsConverted() {
        try {
            sbmlConverter.convert();
            guiBooleanNetworkImporter = sbmlConverter.getGuiBnImporter();
        } catch (Exception e) {
            ExceptionCollector.setExceptionInstance(e);
        }
    }

    @Then("the SBMLConverter creation succeeds")
    public void theSBMLConverterCreationSucceeds() {
        Assert.assertNull(ExceptionCollector.getExceptionInstance());
        Assert.assertNotNull(sbmlConverter.getSBMLModel());
    }

    @Then("the ERODE data structures have been created successfully")
    public void theERODEDataStructuresHaveBeenCreatedSuccessfully() {
        Assert.assertNull(ExceptionCollector.getExceptionInstance());
        Assert.assertNotNull(guiBooleanNetworkImporter);
        Assert.assertNotNull(guiBooleanNetworkImporter.getBooleanNetwork());
        Assert.assertNotNull(sbmlConverter.getInfoImporting());
    }
}
