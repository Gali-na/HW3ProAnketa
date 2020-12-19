package com.example.HWPro3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class WorkWithDataBace {

    public static ListParticipant loadCatalogFromXMLFile(File file) {
        ListParticipant listParticipant = new ListParticipant();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            Element root = document.getDocumentElement();
            NodeList participants = root.getChildNodes();
            for (int i = 0; i < participants.getLength(); i++) {
                Node node = participants.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Participant participant =  getParticipantFromNode(element);
                    if (participant != null) {
                        listParticipant.addParticipant(participant);
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return listParticipant;
    }


    private static  Participant getParticipantFromNode(Element participantElement) {
        if (!participantElement.getTagName().equals("participant")) {
            return null;
        }
        String name = participantElement.getElementsByTagName("name").item(0).getTextContent();
        String lastName = participantElement.getElementsByTagName("lastName").item(0).getTextContent();
        String country = participantElement.getElementsByTagName("country").item(0).getTextContent();


        Participant participant  = new Participant(name, lastName, country);

        return participant;
    }


    public static void saveToXML(ListParticipant listParticipant, String fileName) {
        File file = new File(fileName);
        try {
           boolean k= file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
       String h =file.getAbsolutePath();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();
            Element root = document.createElement("catalog");
            document.appendChild(root);
            for (Participant participant : listParticipant.getParticipants()) {
                Element bookEl = elementFromBook(participant, document);
                root.appendChild(bookEl);
            }
            TransformerFactory traF = TransformerFactory.newInstance();
            Transformer transformer = traF.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult stRes = new StreamResult(fileName);
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, stRes);
        } catch (ParserConfigurationException | TransformerException e) {
        }
    }

    private static Element elementFromBook(Participant participant, Document document) {
        Element participantElement = document.createElement("participant");
        Element name = document.createElement("name");
        name.setTextContent(participant.getName());

        Element lastName = document.createElement("lastName");
        lastName.setTextContent(participant.getLastName());

        Element counrty = document.createElement("country");
        counrty.setTextContent(participant.getCountry());


        participantElement.appendChild(name);
        participantElement.appendChild(lastName);
        participantElement.appendChild(counrty);

        return participantElement;
    }
}
