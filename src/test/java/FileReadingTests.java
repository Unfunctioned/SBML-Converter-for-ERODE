import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBase;
import sbml.conversion.SBMLConverter;

public class FileReadingTests {
    private String path;
    private SBase tree;
    private Exception exception;

    @Given("a valid path to a SBML file")
    public void aValidPathToASBMLFile() {
        path = "./src/test/resources/Trp_reg.sbml";
    }

    @When("the file is read by the module")
    public void theFileIsReadByTheModule() {
        try {
            tree = SBMLConverter.converter.read(path);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("an AST model containing the file data will be available")
    public void anASTModelContainingTheFileDataWillBeAvailable() {
        Assert.assertEquals(tree.getClass(),SBMLDocument.class);
    }
}
