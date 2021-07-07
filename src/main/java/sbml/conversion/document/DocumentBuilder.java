package sbml.conversion.document;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.SBMLDocument;
import sbml.conversion.ModelConverter;

public class DocumentBuilder extends SBMLConverter {

    public DocumentBuilder(@NotNull IBooleanNetwork booleanNetwork) {
        super(booleanNetwork);
        this.modelConverter = new ModelConverter(booleanNetwork);
        this.sbmlDocument = new SBMLDocument(CONFIG.getLevel(), CONFIG.getVersion());
        this.sbmlDocument.setModel(modelConverter.getModel());
    }
}
