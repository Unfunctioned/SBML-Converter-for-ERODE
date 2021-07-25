package sbml.demos;

import org.sbml.jsbml.*;
import org.sbml.jsbml.ext.qual.*;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSBMLReadingDemo {

	public static void main(String[] args) throws IOException, XMLStreamException {
		String path = "Insert path to sbml file here";

		//Read the SBML file and retrieve the data representation
		SBase sbmlEntity = SBMLReader.read(new File(path));
		SBMLDocument sbmlDocument = (SBMLDocument) sbmlEntity;

		//Retrieve the model contained in the document
		Model model = sbmlDocument.getModel();

		//Retrieve the SBML-qual extension
		QualModelPlugin qualModel = (QualModelPlugin) model.getExtension("qual");

		//Retrieve the model's species
		ListOf<QualitativeSpecies> species = qualModel.getListOfQualitativeSpecies();

		//Retrieve the model's transitions
		ListOf<Transition> transitions = qualModel.getListOfTransitions();

		//Retrieve a specific transition
		Transition transition = transitions.get(0);

		//Retrieve inputs
		ListOf<Input> inputs = transition.getListOfInputs();

		//Retrieve outputs
		ListOf<Output> outputs = transition.getListOfOutputs();

		//Retrieve function terms
		ListOf<FunctionTerm> functionTerms = transition.getListOfFunctionTerms();

		//Retrieve a specific function term
		FunctionTerm functionTerm = functionTerms.get(0);

		//Retrieve the AST for the mathematical expression contained in the function term
		ASTNode root = functionTerm.getMath();

	}
}