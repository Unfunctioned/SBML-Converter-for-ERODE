package sbml.conversion.document;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import it.imt.erode.importing.InfoBooleanNetworkImporting;
import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBase;
import sbml.configurations.SBMLConfiguration;
import sbml.conversion.model.IModelConverter;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

public abstract class SBMLConverter implements ISBMLConverter {

    public static SBase read(String path) throws IOException, XMLStreamException {
        return SBMLReader.read(new File(path));
    }

    public static ISBMLConverter create(@NotNull SBMLDocument sbmlDocument) throws IOException {
        return new DocumentReader(sbmlDocument);
    }

    public static ISBMLConverter create(@NotNull IBooleanNetwork booleanNetwork) {
        return new DocumentWriter(booleanNetwork);
    }

    protected static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

    protected IModelConverter modelConverter;

    protected SBMLDocument sbmlDocument;

    protected InfoBooleanNetworkImporting infoImporting;
    protected GUIBooleanNetworkImporter guiBnImporter;
    protected IBooleanNetwork booleanNetwork;

    public SBMLConverter(@NotNull SBMLDocument sbmlDocument) throws IOException {
        this.sbmlDocument = sbmlDocument;
    }

    public SBMLConverter(IBooleanNetwork booleanNetwork) {
        this.booleanNetwork = booleanNetwork;
    }

    @Override
    public GUIBooleanNetworkImporter getGuiBnImporter() {
        return this.guiBnImporter;
    }

    @Override
    public IModelConverter getSBMLModel() {
        return this.modelConverter;
    }

    @Override
    public InfoBooleanNetworkImporting getInfoImporting() {
        return this.infoImporting;
    }

    @Override
    public SBMLDocument getSbmlDocument() {
        return this.sbmlDocument;
    }
}
