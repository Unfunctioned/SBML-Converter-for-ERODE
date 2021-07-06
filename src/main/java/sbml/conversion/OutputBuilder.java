package sbml.conversion;

import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.OutputTransitionEffect;
import sbml.configurations.SBMLConfiguration;

public class OutputBuilder {
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();
    private static final String DEFAULT_NAME = "Output";

    public Output build(String speciesId, int id) {
        Output o = new Output(CONFIG.getLevel(), CONFIG.getVersion());
        o.setId(DEFAULT_NAME + id);
        o.setQualitativeSpecies(speciesId);
        o.setTransitionEffect(OutputTransitionEffect.assignmentLevel);
        return o;
    }
}
