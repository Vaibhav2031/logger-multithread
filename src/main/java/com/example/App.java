package com.example;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.example.utility.FileOperations;

public class App {

    public static void main(String[] args) {
        File logFolder = Constants.LOG_FOLDER;
        File[] files = logFolder.listFiles();

        if (files != null) {
            ExecutorService executor = Executors.newFixedThreadPool(files.length);
            for (File file : files) {
                executor.execute((Runnable) () -> { // Cast the lambda expression to Runnable
                    FileOperations fileOperations = new FileOperations();
                    fileOperations.readFile(file);
                });
            }
            executor.shutdown();
        }
    }
}
