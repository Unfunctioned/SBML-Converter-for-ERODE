package sbml.demos;

import it.imt.erode.importing.booleannetwork.GUIBooleanNetworkImporter;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLWriter;
import sbml.conversion.SBMLConverter;

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
       String path = "D:/Repositories/SBML-Converter-for-ERODE/src/main/resources/sbml/demos/CorticalAreaDevelopment.sbml";
        SBMLDocument sbmlDocument = (SBMLDocument) SBMLConverter.read(path);


        SBMLConverter converter = new SBMLConverter(sbmlDocument);
        GUIBooleanNetworkImporter guiBooleanNetworkImporter = converter.getGuiBnImporter();
        //--------------------------------------------------------------------------------------
        converter = new SBMLConverter(guiBooleanNetworkImporter.getBooleanNetwork());
        sbmlDocument = converter.getSbmlDocument();

        print(sbmlDocument);
    }

    private static void print(SBMLDocument sbmlDocument) {
        String writePath = System.getProperty("user.dir");
        System.out.println("Working Directory = " + writePath);
        try {
            File sbmlFile = new File("SBMLFile.sbml");
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
