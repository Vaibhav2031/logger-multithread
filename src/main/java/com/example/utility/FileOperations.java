package com.example.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.Constants;

public class FileOperations {

    File logFolder = Constants.LOG_FOLDER;


    private static final Logger LOGGER = LogManager.getLogger(FileOperations.class.getName());

    public void readFile(File file) {
        String threadName = Thread.currentThread().getName();
        String message = String.format("%s is reading file: %s", threadName, file.getName());
        LOGGER.info(message);

        try (Stream<String> lines = Files.lines(Paths.get(logFolder+"/"+ file.getName()))) {
            lines.forEach(LOGGER::info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
