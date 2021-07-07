package sbml.conversion.document;

import it.imt.erode.importing.InfoBooleanNetworkImporting;
import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.sbml.jsbml.SBMLDocument;
import sbml.conversion.ModelConverter;

public interface ISBMLConverter {

    GUIBooleanNetworkImporter getGuiBnImporter();

    ModelConverter getSBMLModel();

    InfoBooleanNetworkImporting getInfoImporting();

    SBMLDocument getSbmlDocument();
}
