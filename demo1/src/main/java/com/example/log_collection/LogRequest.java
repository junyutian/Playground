package com.example.log_collection;

import java.io.*;

/**
 * This is the class to validate and convert parameters in the request
 *
 * @author junyu.tian
 */
public class LogRequest {

    /**
     * If no path provided, use default one
     *
     * @param path the path from request
     * @return
     */
    public static String getPath(String path) {
        if (path == null || path.length() == 0) {
            return Constants.DEFAULT_PATH;
        }
        return path;
    }

    /**
     * Check whether request is valid or not
     * filename is mandatory, others are optional
     *
     * @param filename the filename from request
     * @return whether request is valid or not
     */
    public static String validFilename(String path, String filename) {
        if (filename == null || filename.length() == 0) {
            return Constants.ErrorMessage.MISSING_FILENAME;
        }

        if (!new File(path + "/" + filename).exists()) {
            return Constants.ErrorMessage.FILENAME_NOT_EXIST;
        }

        // valid filename
        return null;
    }

    /**
     * Convert entries
     * If not provided, set default for it
     *
     * @param entries the number of lines for response
     * @return converted entries in integer
     */
    public static int getEntries(String entries) {
        if (entries == null || entries.length() == 0) {
            return Constants.DEFAULT_ENTRIES;
        }

        try {
            return Integer.parseInt(entries);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
