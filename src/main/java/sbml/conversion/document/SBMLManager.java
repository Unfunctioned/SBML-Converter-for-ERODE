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

public class SBMLManager {

    public static SBase read(String path) throws IOException, XMLStreamException {
        return SBMLReader.read(new File(path));
    }

    public static ISBMLConverter create(@NotNull SBMLDocument sbmlDocument) throws IOException {
        return new DocumentReader(sbmlDocument);
    }

    public static ISBMLConverter create(@NotNull IBooleanNetwork booleanNetwork) {
        return new DocumentWriter(booleanNetwork);
    }

}
