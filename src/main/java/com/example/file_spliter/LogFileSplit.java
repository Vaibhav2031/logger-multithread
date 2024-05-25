package com.example.file_spliter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LogFileSplit {
    public void splitFile(Path filePath, int linesPerFile) throws IOException, InterruptedException {
        String destinationPath = "src/logs/child";
        ExecutorService executor = Executors.newFixedThreadPool(10); // create a thread pool

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line = reader.readLine();
            int lineCount = 1;
            int fileCount = 0;
            Path childPath = Paths.get(destinationPath + "/child_" + fileCount + ".log");

            while (line != null) {
                if (lineCount > linesPerFile) {
                    lineCount = 0;
                    fileCount++;
                    childPath = Paths.get(destinationPath + "/child_" + fileCount + ".log");
                }

                final String lineToWrite = line;
                final Path pathToWrite = childPath;

                executor.submit(() -> {
                    try (BufferedWriter writer = Files.newBufferedWriter(pathToWrite, StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND)) {
                        writer.write(lineToWrite);
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                lineCount++;
                line = reader.readLine();
            }
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS); // wait for all tasks to finish
    }
}
