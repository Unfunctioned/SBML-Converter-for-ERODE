package sbml.conversion.model;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.qualmodel.QualModelConverter;

class ModelWriter extends ModelConverter {

    public ModelWriter(IBooleanNetwork booleanNetwork) {
        super(booleanNetwork);
        this.model = new Model(CONFIG.getLevel(),CONFIG.getVersion());
        this.qualModelConverter = QualModelConverter.create(booleanNetwork, model);
        this.packageSBML();
    }

    private void packageSBML() {
        QualModelPlugin qualModelPlugin = qualModelConverter.getSbmlQualModel();
        model.addExtension(EXTENSION_NAME,qualModelPlugin);
    }
}
