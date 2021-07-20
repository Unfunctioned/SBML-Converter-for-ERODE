package sbml.test.framework.qualmodel;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;
import sbml.conversion.qualmodel.IQualModelConverter;
import sbml.test.framework.TestDataManager;

public class QualModelDataManager extends TestDataManager {

    private QualModelPlugin qualModelPlugin;
    private IQualModelConverter qualModelConverter;

    private IBooleanNetwork booleanNetwork;

    private Model model;

    public QualModelPlugin getQualModelPlugin() {
        if(qualModelPlugin == null && qualModelConverter != null)
            this.qualModelPlugin = qualModelConverter.getSbmlQualModel();
        return qualModelPlugin;
    }

    public void setQualModelPlugin(QualModelPlugin qualModelPlugin) {
        this.qualModelPlugin = qualModelPlugin;
    }

    public IQualModelConverter getQualModelConverter() {
        return qualModelConverter;
    }

    public void setQualModelConverter(IQualModelConverter qualModelConverter) {
        this.qualModelConverter = qualModelConverter;
    }

    public IBooleanNetwork getBooleanNetwork() {
        return booleanNetwork;
    }

    public void setBooleanNetwork(IBooleanNetwork booleanNetwork) {
        this.booleanNetwork = booleanNetwork;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
