package sbml.conversion.document;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.SBMLDocument;
import sbml.conversion.model.ModelConverter;

class DocumentWriter extends SBMLConverter {

    public DocumentWriter(@NotNull IBooleanNetwork booleanNetwork) {
        super(booleanNetwork);
        this.modelConverter = ModelConverter.create(booleanNetwork);
        this.sbmlDocument = new SBMLDocument(CONFIG.getLevel(), CONFIG.getVersion());
        this.sbmlDocument.setModel(modelConverter.getModel());
    }
}
