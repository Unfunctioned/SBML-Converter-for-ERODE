package sbml.conversion.qualmodel;

import it.imt.erode.booleannetwork.interfaces.IBooleanNetwork;
import org.jetbrains.annotations.NotNull;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.qual.QualModelPlugin;

public class QualModelManager {
    public static IQualModelConverter create(@NotNull QualModelPlugin qualModelPlugin) {
        return new QualModelReader(qualModelPlugin);
    }

    public static IQualModelConverter create(@NotNull IBooleanNetwork booleanNetwork, Model model) {
        return new QualModelWriter(booleanNetwork, model);
    }
}
