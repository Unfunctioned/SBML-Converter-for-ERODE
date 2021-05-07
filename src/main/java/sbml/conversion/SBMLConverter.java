package sbml.conversion;

import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBase;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

public class SBMLConverter {

    public static  SBMLConverter converter = new SBMLConverter();

    public SBase read(String path) throws IOException, XMLStreamException {
        return SBMLReader.read(new File(path));
    }
}
