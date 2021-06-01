package sbml.demos;

import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.importing.InfoBooleanNetworkImporting;
import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.sbml.jsbml.SBMLDocument;
import sbml.conversion.SBMLConverter;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ErodeExporter {

    /**
     * Small demo creating a .ode file that can be read by ERODE
     * @param args - Not needed
     * @throws IOException
     * @throws XMLStreamException
     */
    public static void main(String[] args) throws IOException, XMLStreamException {
        String path = "D:/Repositories/SBML-Converter-for-ERODE/src/main/resources/sbml/demos/Trp_reg.sbml";
        SBMLDocument sbmlDocument = (SBMLDocument) SBMLConverter.read(path);
        SBMLConverter sbmlConverter = new SBMLConverter(sbmlDocument);

        GUIBooleanNetworkImporter guiBooleanNetworkImporter = sbmlConverter.convert();
        System.out.println(guiBooleanNetworkImporter.getBooleanNetwork().getSpecies().toString());
        GUIBooleanNetworkImporter.printToBNERODEFIle(guiBooleanNetworkImporter.getBooleanNetwork(),guiBooleanNetworkImporter.getInitialPartition(),
                "Test.ode", null, true, null, null, false);
    }
}
