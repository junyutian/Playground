package com.example.log_collection;

public class Constants {
    public static final String CONTENT_TYPE = "text/html"; // Response type

    // Request fields
    public static final String FILENAME = "filename";
    public static final String ENTRIES = "entries";
    public static final int DEFAULT_ENTRIES = 10;
    public static final String KEYWORD = "keyword";
    public static final String PATH = "path";
    public static final String DEFAULT_PATH = "/var/log/";

    public static class ErrorMessage {
        public static final String MISSING_FILENAME =
                "<h2 style=\"color:red;\">Missing filename</h2>";
        public static final String FILENAME_NOT_EXIST =
                "<h2 style=\"color:red;\">Filename is not exist</h2>";
        public static final String INVALID_ENTRIES =
                "<h2 style=\"color:red;\">Entries should be positive integer</h2>";
    }
}
