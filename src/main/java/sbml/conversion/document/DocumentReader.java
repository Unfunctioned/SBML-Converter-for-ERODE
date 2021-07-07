package sbml.conversion.document;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.crn.interfaces.ISpecies;
import it.imt.erode.importing.InfoBooleanNetworkImporting;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.SBMLDocument;
import sbml.conversion.model.ModelConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DocumentReader extends SBMLConverter {

    public DocumentReader(@NotNull SBMLDocument sbmlDocument) throws IOException {
        super(sbmlDocument);
        this.modelConverter = ModelConverter.create(sbmlDocument.getModel());
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
}
