package sbml.test.framework.model;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import org.sbml.jsbml.Model;
import sbml.conversion.model.IModelConverter;
import sbml.test.framework.TestDataManager;

public class ModelDataManager extends TestDataManager {
    private Model model;
    private IModelConverter modelConverter;
    private IBooleanNetwork booleanNetwork;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public IModelConverter getModelConverter() {
        return modelConverter;
    }

    public void setModelConverter(IModelConverter modelConverter) {
        this.modelConverter = modelConverter;
    }

    public IBooleanNetwork getBooleanNetwork() {
        return booleanNetwork;
    }

    public void setBooleanNetwork(IBooleanNetwork booleanNetwork) {
        this.booleanNetwork = booleanNetwork;
    }
}
