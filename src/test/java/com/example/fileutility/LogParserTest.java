package com.example.fileutility;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertTrue;



class LogParserTest {


    @Test
    void testParseFile() {
        File logFile = new File("src/test_logs/test.log"); // replace with your log file path


        File csvFile = new File("src/test_logs/test.csv"); // replace with your output file path
        assertTrue(csvFile.exists(), "CSV file should be created");

       try (Stream<String> lines = Files.lines(Paths.get(logFile.getName()));
                PrintWriter writer = new PrintWriter(new FileWriter(csvFile, true))) {
            lines.forEach(line -> {
                String[] parts = line.split(" ", 4);
                if (parts.length == 4) {
                    String date = parts[0];
                    String time = parts[1];
                    String severity = parts[2];
                    String message = parts[3];

                    // Format the output line for CSV
                    String csvLine = String.format("%s,%s,%s,%s", date, time, severity, message);
                    writer.println(csvLine);
            }
        });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}