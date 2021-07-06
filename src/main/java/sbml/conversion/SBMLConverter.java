package sbml.conversion;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import it.imt.erode.importing.InfoBooleanNetworkImporting;
import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBase;
import sbml.configurations.SBMLConfiguration;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SBMLConverter {
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

    public static SBase read(String path) throws IOException, XMLStreamException {
        return SBMLReader.read(new File(path));
    }

    private GUIBooleanNetworkImporter guiBnImporter;
    private InfoBooleanNetworkImporting infoImporting;

    private ModelConverter modelConverter;

    private SBMLDocument sbmlDocument;

    public SBMLConverter(@NotNull SBMLDocument sbmlDocumentModel) {
        this.sbmlDocument = sbmlDocumentModel;
        modelConverter = new ModelConverter(sbmlDocumentModel.getModel());
    }

    public SBMLConverter(IBooleanNetwork booleanNetwork) {
        this.modelConverter = new ModelConverter(booleanNetwork);
        System.out.println("Packaging SBML model");
        this.sbmlDocument = new SBMLDocument(CONFIG.getLevel(), CONFIG.getVersion());
        Model model = modelConverter.getModel();
        sbmlDocument.setModel(modelConverter.getModel());
    }

    public void toErode() throws IOException {
        this.guiBnImporter = new GUIBooleanNetworkImporter(null, null, null);
        this.infoImporting = guiBnImporter.importBooleanNetwork(true,true, true,
                modelConverter.getName(), new ArrayList<ArrayList<String>>(), new LinkedHashMap<String, IUpdateFunction>(),
                new ArrayList<ArrayList<String>>(),null);
        this.addSpecies(modelConverter.getErodeSpecies());
        this.setUpdateFunctions(modelConverter.getErodeUpdateFunctions());
    }

    private void addSpecies(LinkedHashMap<String, ISpecies> erodeSpecies) {
        IBooleanNetwork bn = guiBnImporter.getBooleanNetwork();
        for(ISpecies s : erodeSpecies.values()) {
            bn.addSpecies(s);
        }
    }

    private void setUpdateFunctions(LinkedHashMap<String, IUpdateFunction> updateFunctions) {
        IBooleanNetwork bn = guiBnImporter.getBooleanNetwork();
        bn.setAllUpdateFunctions(updateFunctions);
    }

    public GUIBooleanNetworkImporter getGuiBnImporter() {
        return this.guiBnImporter;
    }

    public ModelConverter getSBMLModel() {
        return this.modelConverter;
    }

    public InfoBooleanNetworkImporting getInfoImporting() {
        return this.infoImporting;
    }

    public SBMLDocument getSbmlDocument() {
        return this.sbmlDocument;
    }
}
