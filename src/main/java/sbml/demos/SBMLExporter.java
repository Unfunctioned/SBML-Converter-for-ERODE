package sbml.demos;

import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLWriter;
import sbml.conversion.document.ISBMLConverter;
import sbml.conversion.document.SBMLManager;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

public class SBMLExporter {
    /**
     * Small demo converting an .sbml file to .ode format
     * and back
     * @param args
     */
    public static void main(String[] args) throws IOException, XMLStreamException {
       String path = "D:/Repositories/SBML-Converter-for-ERODE/src/main/resources/sbml/demos/DemoNetwork.sbml";
        SBMLDocument sbmlDocument = (SBMLDocument) SBMLManager.read(path);


        ISBMLConverter converter = SBMLManager.create(sbmlDocument);
        GUIBooleanNetworkImporter guiBooleanNetworkImporter = converter.getGuiBnImporter();
        //--------------------------------------------------------------------------------------
        converter = SBMLManager.create(guiBooleanNetworkImporter.getBooleanNetwork());
        sbmlDocument = converter.getSbmlDocument();

        print(sbmlDocument);
    }

    private static void print(SBMLDocument sbmlDocument) {
        String writePath = System.getProperty("user.dir");
        System.out.println("Working Directory = " + writePath);
        try {
            File sbmlFile = new File("DemoSBMLFile.sbml");
            if(sbmlFile.createNewFile()) {
                System.out.println("Created file: " + sbmlFile.getName() + "at path: " + sbmlFile.getPath());
            }
            System.out.println("Writing to file...");
            SBMLWriter.write(sbmlDocument, sbmlFile, "SBMLConverter", "1.0");
            System.out.println("Finished");
        } catch (IOException | XMLStreamException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
