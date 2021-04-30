import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBase;
import sbml.conversion.SBMLConverter;

public class FileReadingTests {
    private String path;
    private SBMLConverter converter;
    private SBase tree;
    private String dir;
    private String fullPath;

    @Given("a valid path to a SBML file")
    public void aValidPathToASBMLFile() {
        dir = System.getProperty("user.dir");
        System.out.println(dir);
        path = "./src/test/resources/Trp_reg.sbml";
    }

    @When("the file is read by the module")
    public void theFileIsReadByTheModule() {
        try {
            fullPath = dir+path;
            tree = SBMLConverter.converter.read(path);
        } catch (Exception e) {
            Assert.fail();
        }

    }

    @Then("an AST model containing the file data will be available")
    public void anASTModelContainingTheFileDataWillBeAvailable() {
        Assert.assertTrue(tree.getClass().equals(SBMLDocument.class));
    }
}
