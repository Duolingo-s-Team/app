package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XML {

    private static XML constructor;

    public static XML getConstructor() {
        if (constructor == null)
            return new XML();
        else {
            return constructor;
        }
    }

    private XML() {

    }

    public boolean readXML(File path) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Nodo que contiene los campos
            Element eConfig = doc.createElement("Config");
            doc.appendChild(eConfig);

            // Campos dentro del nodo
            Element eIPServer = doc.createElement("ipserver");
            eIPServer.appendChild(doc.createTextNode(" 91.126.22.107"));
            Element ePort = doc.createElement("port");
            ePort.appendChild(doc.createTextNode("25565"));
            Element eUsername = doc.createElement("username");
            eUsername.appendChild(doc.createTextNode("#USERNAME"));
            Element ePassword = doc.createElement("password");
            ePassword.appendChild(doc.createTextNode("contrasena"));
            eConfig.appendChild(eIPServer);
            eConfig.appendChild(ePort);
            eConfig.appendChild(eUsername);
            eConfig.appendChild(ePassword);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path+"config.xml"));

            transformer.transform(source, result);

        } catch(Exception e) {
            return false;
        }

        return true;

    }

}
