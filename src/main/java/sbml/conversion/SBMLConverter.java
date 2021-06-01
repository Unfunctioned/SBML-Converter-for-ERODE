package sbml.conversion;

import it.imt.erode.booleannetwork.implementations.BooleanNetwork;
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
import java.util.Map;

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
        return guiBnImporter;
    }

    public GUIBooleanNetworkImporter convert() throws IOException {
        guiBnImporter = new GUIBooleanNetworkImporter(null, null, null);
        //ArrayList<ArrayList<String>> initialConcentrations --> what are they in SBML? where to find?
        //Boolean updateFunctions --> LinkedHashMap<String,IUpdateFunction>()
        //ArrayList<ArrayList<String>> -> initialPartition --> what?
        this.infoImporting = guiBnImporter.importBooleanNetwork(true,true, true,
                sbmlModel.getName(), new ArrayList<ArrayList<String>>(), new LinkedHashMap<String, IUpdateFunction>(),
                new ArrayList<ArrayList<String>>(),null);
        this.setModelName("TestNetwork");
        this.addSpecies(sbmlModel.getErodeSpecies());
        this.setUpdateFunctions(sbmlModel.getErodeUpdateFunctions());
        return this.guiBnImporter;
    }

    private void setModelName(String testNetwork) {
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
