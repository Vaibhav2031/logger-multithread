package com.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.file_spliter.LogFileSplit;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Path filePath = Paths.get("src/logs/parent.log");

        LogFileSplit logFileSplit = new LogFileSplit();
        try {
            logFileSplit.splitFile(filePath, 100);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
