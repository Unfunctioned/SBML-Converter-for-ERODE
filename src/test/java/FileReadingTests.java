import com.thoughtworks.xstream.io.StreamException;
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

    @Given("a valid path to an erroneus SBML file")
    public void a_valid_path_to_an_erroneus_sbml_file() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the file is read by the module")
    public void theFileIsReadByTheModule() {
        try {
            tree = SBMLConverter.read(path);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("an AST model containing the file data will be available")
    public void anASTModelContainingTheFileDataWillBeAvailable() {
        Assert.assertEquals(tree.getClass(),SBMLDocument.class);
    }


    @Then("parsing will fail with an exception")
    public void parsingWillFailWithAnException() {
        Assert.assertEquals(exception.getClass(), StreamException.class);
    }
}
