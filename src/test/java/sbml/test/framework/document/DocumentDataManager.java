package sbml.test.framework.document;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.sbml.jsbml.SBMLDocument;
import sbml.conversion.document.ISBMLConverter;
import sbml.test.framework.TestDataManager;

public class DocumentDataManager extends TestDataManager {
    private SBMLDocument sbmlDocument;
    private ISBMLConverter sbmlConverter;
    private GUIBooleanNetworkImporter guiBooleanNetworkImporter;
    private IBooleanNetwork booleanNetwork;

    public SBMLDocument getSbmlDocument() {
        return sbmlDocument;
    }

    public void setSbmlDocument(SBMLDocument sbmlDocument) {
        this.sbmlDocument = sbmlDocument;
    }

    public ISBMLConverter getSbmlConverter() {
        return sbmlConverter;
    }

    public void setSbmlConverter(ISBMLConverter sbmlConverter) {
        this.sbmlConverter = sbmlConverter;
    }

    public GUIBooleanNetworkImporter getGuiBooleanNetworkImporter() {
        if(guiBooleanNetworkImporter == null && sbmlConverter != null)
            this.guiBooleanNetworkImporter = sbmlConverter.getGuiBnImporter();
        return guiBooleanNetworkImporter;
    }

    public void setGuiBooleanNetworkImporter(GUIBooleanNetworkImporter guiBooleanNetworkImporter) {
        this.guiBooleanNetworkImporter = guiBooleanNetworkImporter;
    }

    public IBooleanNetwork getBooleanNetwork() {
        return booleanNetwork;
    }

    public void setBooleanNetwork(IBooleanNetwork booleanNetwork) {
        this.booleanNetwork = booleanNetwork;
    }
}
