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
import sbml.models.SBMLModel;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SBMLConverter {

    public static SBase read(String path) throws IOException, XMLStreamException {
        return SBMLReader.read(new File(path));
    }

    private GUIBooleanNetworkImporter guiBnImporter;
    private InfoBooleanNetworkImporting infoImporting;

    private SBMLModel sbmlModel;

    public SBMLConverter(@NotNull SBMLDocument sbmlDocumentModel) {
        sbmlModel = new SBMLModel(sbmlDocumentModel.getModel());
    }

    public GUIBooleanNetworkImporter getGuiBnImporter() {
        return this.guiBnImporter;
    }

    public SBMLModel getSBMLModel() {
        return this.sbmlModel;
    }

    public InfoBooleanNetworkImporting getInfoImporting() {
        return this.infoImporting;
    }

    public void convert() throws IOException {
        this.guiBnImporter = new GUIBooleanNetworkImporter(null, null, null);
        this.infoImporting = guiBnImporter.importBooleanNetwork(true,true, true,
                sbmlModel.getName(), new ArrayList<ArrayList<String>>(), new LinkedHashMap<String, IUpdateFunction>(),
                new ArrayList<ArrayList<String>>(),null);
        this.addSpecies(sbmlModel.getErodeSpecies());
        this.setUpdateFunctions(sbmlModel.getErodeUpdateFunctions());
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
}
