package sbml.demos;

import org.sbml.jsbml.*;
import org.sbml.jsbml.ext.qual.*;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSBMLWritingDemo {

	private static final int LEVEL = 3;
	private static final int VERSION = 1;

	public static void main(String[] args) throws IOException, XMLStreamException {

		//Demo: Adding an extension to an existing SBML Document

		//Initialisations
		SBMLDocument sbmlDocument = new SBMLDocument(LEVEL, VERSION);
		Model model = new Model(LEVEL, VERSION);
		QualModelPlugin qualModel = new QualModelPlugin(model);

		//Create model content
		ListOf<QualitativeSpecies> species = new ListOf<>(LEVEL, VERSION);
		ListOf<Transition> transitions = new ListOf<>(LEVEL, VERSION);

		//Add model to the SBML representation
		sbmlDocument.setModel(model);

		//Add SBML-qual extension
		model.addExtension("qual", qualModel);

		//Add model content to the extesion model
		species.setParent(qualModel.getModel());
		qualModel.setListOfQualitativeSpecies(species);

		transitions.setParent(qualModel.getModel());
		qualModel.setListOfTransitions(transitions);

	}
}