package sbml.conversion;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import it.imt.erode.importing.InfoBooleanNetworkImporting;
import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBase;
import sbml.configurations.SBMLConfiguration;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SBMLConverter {

    public static SBase read(String path) throws IOException, XMLStreamException {
        return SBMLReader.read(new File(path));
    }

    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

    private ModelConverter modelConverter;

    private SBMLDocument sbmlDocument;

    private InfoBooleanNetworkImporting infoImporting;
    private GUIBooleanNetworkImporter guiBnImporter;
    private IBooleanNetwork booleanNetwork;

    public SBMLConverter(@NotNull SBMLDocument sbmlDocument) throws IOException {
        this.sbmlDocument = sbmlDocument;
        this.modelConverter = new ModelConverter(sbmlDocument.getModel());

        this.guiBnImporter = new GUIBooleanNetworkImporter(null, null, null);
        this.infoImporting = createErodeModel();
        this.booleanNetwork = guiBnImporter.getBooleanNetwork();
        buildErodeModel();
    }

    private InfoBooleanNetworkImporting createErodeModel() throws IOException {

        InfoBooleanNetworkImporting importing = guiBnImporter.importBooleanNetwork(
                true,true, true,
                modelConverter.getName(),
                new ArrayList<ArrayList<String>>(),
                new LinkedHashMap<String, IUpdateFunction>(),
                new ArrayList<ArrayList<String>>(),
                null);
        return importing;
    }

    private void buildErodeModel() {
        this.initializeSpecies(modelConverter.getErodeSpecies());
        this.initializeUpdateFunctions(modelConverter.getErodeUpdateFunctions());
    }

    private void initializeSpecies(List<ISpecies> erodeSpecies) {
        for(ISpecies s : erodeSpecies)
            this.booleanNetwork.addSpecies(s);
    }

    private void initializeUpdateFunctions(LinkedHashMap<String, IUpdateFunction> updateFunctions) {
        this.booleanNetwork.setAllUpdateFunctions(updateFunctions);
    }

    public SBMLConverter(@NotNull IBooleanNetwork booleanNetwork) {
        this.modelConverter = new ModelConverter(booleanNetwork);
        this.sbmlDocument = new SBMLDocument(CONFIG.getLevel(), CONFIG.getVersion());
        this.sbmlDocument.setModel(modelConverter.getModel());
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
