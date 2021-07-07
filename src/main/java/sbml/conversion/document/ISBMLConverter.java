package sbml.conversion.document;

import it.imt.erode.importing.InfoBooleanNetworkImporting;
import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.sbml.jsbml.SBMLDocument;
import sbml.conversion.model.IModelConverter;

public interface ISBMLConverter {

    GUIBooleanNetworkImporter getGuiBnImporter();

    IModelConverter getSBMLModel();

    InfoBooleanNetworkImporting getInfoImporting();

    SBMLDocument getSbmlDocument();
}
