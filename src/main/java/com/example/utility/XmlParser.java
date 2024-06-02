package com.example.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.Constants;

public class XmlParser implements FileParser {

    File logFolder = Constants.LOG_FOLDER;
    File outputLogFolder = Constants.OUTPUT_LOG_FOLDER;

    private static final Logger LOGGER = LogManager.getLogger(XmlParser.class.getName());

    @Override
    public void parseFile(File file) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("%s is parsing file: %s", Thread.currentThread().getName(), file.getName()));
        }

        String fileName = file.getName();
        String csvFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".csv";

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // Disable DTDs
            dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

            // Disable external DTDs and schemas
            dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("entry"); // replace "*" with your XML element name

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputLogFolder + "/" + csvFileName, true))) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        NodeList childNodes = element.getElementsByTagName("*"); // replace "*" with your XML element
                                                                                 // name
                        if (childNodes.getLength() > 0) {
                            writer.println(childNodes.item(0).getTextContent() + ","
                                    + childNodes.item(1).getTextContent() + ","
                                    + childNodes.item(2).getTextContent());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}