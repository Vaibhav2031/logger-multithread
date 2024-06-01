package com.example;

import java.io.File;

public class Constants {
    private Constants() {
        // private constructor to hide the implicit public one
    }

    public static final File LOG_FOLDER = new File("src/logs");
    public static final File OUTPUT_LOG_FOLDER = new File("src/output_logs");
}
