package com.example.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.Constants;

public class LogParser implements FileParser {

    File logFolder = Constants.LOG_FOLDER;
    File outputLogFolder = Constants.OUTPUT_LOG_FOLDER;

    private static final Logger LOGGER = LogManager.getLogger(LogParser.class.getName());

    @Override
    public void parseFile(File file) {

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("%s is parsing file: %s", Thread.currentThread().getName(), file.getName()));
        }

        String fileName = file.getName();
        String csvFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".csv";

        try (Stream<String> lines = Files.lines(Paths.get(logFolder + "/" + fileName));
                PrintWriter writer = new PrintWriter(new FileWriter(outputLogFolder + "/" + csvFileName, true))) {
            lines.forEach(line -> writer.println(line.replace(" ", ",")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
