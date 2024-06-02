package com.example.utility;

public class FileParserFactory {
    public FileParser createParser(String fileType) {
        if (fileType.equalsIgnoreCase("log")) {
            return new LogParser();
        } else if (fileType.equalsIgnoreCase("xml")) {
            return new XmlParser();
        }
        throw new IllegalArgumentException("Invalid file type: " + fileType);
    }
}