package sbml.demos;

import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.sbml.jsbml.SBMLDocument;
import sbml.conversion.document.ISBMLConverter;
import sbml.conversion.document.SBMLConverter;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class ErodeExporter {

    /**
     * Small demo creating a .ode file that can be read by ERODE
     * @param args - Not needed
     * @throws IOException
     * @throws XMLStreamException
     */

    public static void main(String[] args) throws IOException, XMLStreamException {
        String path = "D:/Repositories/SBML-Converter-for-ERODE/src/main/resources/sbml/demos/CorticalAreaDevelopment.sbml";
        SBMLDocument sbmlDocument = (SBMLDocument) SBMLConverter.read(path);


        ISBMLConverter sbmlConverter = SBMLConverter.create(sbmlDocument);

        GUIBooleanNetworkImporter guiBooleanNetworkImporter = sbmlConverter.getGuiBnImporter();
        System.out.println(guiBooleanNetworkImporter.getBooleanNetwork().getSpecies().toString());
        GUIBooleanNetworkImporter.printToBNERODEFIle(guiBooleanNetworkImporter.getBooleanNetwork(),guiBooleanNetworkImporter.getInitialPartition(),
                "Test.ode", null, true, null, null, false);
    }
}
