package com.example.fileutility;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileOperations {

    private static final Logger LOGGER = LogManager.getLogger(FileOperations.class.getName());

    public void readFile(File file) {
        String threadName = Thread.currentThread().getName();
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        String message = String.format("%s is reading file: %s", threadName, fileName);
        LOGGER.info(message);

        FileParserFactory factory = new FileParserFactory();
        FileParser parser = factory.createParser(fileType);

        parser.parseFile(file);
    }
}
