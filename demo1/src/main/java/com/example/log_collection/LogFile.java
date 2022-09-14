package com.example.log_collection;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class to process the log file
 *
 * @author junyu.tian
 */
public class LogFile {
    Logger logger = Logger.getLogger(LogFile.class.getName());

    File file;

    LogFile(String path, String filename) {
        file = new File(path + "/" + filename);
    }

    /**
     * Get the last lines from the file with filtering support
     *
     * @param lastLines the number of last lines
     * @param text the keyword for filtering
     * @return the list of matched lines
     */
    public List<String> getLastLines(int lastLines, String text) {
        List<String> lines = new ArrayList<>();

        int numLinesRead = 0;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            long fileLength = file.length() - 1;
            // Empty file
            if (fileLength < 0) {
                return lines;
            }
            
            // Read file from the end
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                for (long pointer = fileLength; pointer >= 0; pointer--) {
                    randomAccessFile.seek(pointer);
                    byte b = (byte) randomAccessFile.read();
                    if (b == '\n') {
                        numLinesRead++;
                        // Skip last '\n'
                        if (numLinesRead == 1) {
                            continue;
                        }

                        String line = reverseByteInLine(byteArrayOutputStream);
                        if (text == null || text.length() == 0 || line.contains(text)) {
                            lines.add(line);
                        } else {
                            numLinesRead--;
                        }

                        // Reset ByteArrayOutputStream
                        byteArrayOutputStream.reset();

                        // (Last line often terminated with a line separator)
                        if (numLinesRead == (lastLines + 1))
                            break;
                    }
                    byteArrayOutputStream.write(b);
                    fileLength = fileLength - pointer;
                }
                // Add the first line
                if (byteArrayOutputStream.size() > 0) {
                    String line = reverseByteInLine(byteArrayOutputStream);
                    if (text == null || text.length() == 0 || line.contains(text)) {
                        lines.add(line);
                    }
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        // Reverse lines
        Collections.reverse(lines);
        return lines;
    }

    /**
     * Reverse the bytes in each line
     *
     * @param byteArrayOutputStream the byte array output stream
     * @return the reversed line
     */
    protected static String reverseByteInLine(ByteArrayOutputStream byteArrayOutputStream) {
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int left = 0;
        int right = byteArray.length - 1;

        while (left < right) {
            byte temp = byteArray[right];
            byteArray[right] = byteArray[left];
            byteArray[left] = temp;
            left++;
            right--;
        }

        return new String(byteArray).trim();
    }
}
