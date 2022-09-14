package com.example.log_collection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the test class for LogFile
 *
 * @author junyu.tian
 */
public class LogFileTest {

    @Test
    public void testGetLastLines() {
        LogFile logFile = new LogFile("./src/test/resources", "test1.log");

        // Test number of asked lines less than total lines
        List<String> lastLines = logFile.getLastLines(2, null);
        assertEquals(2, lastLines.size());
        assertEquals("line2", lastLines.get(0));
        assertEquals("skip", lastLines.get(1));

        // Test number of asked lines bigger than total lines
        lastLines = logFile.getLastLines(4, null);
        assertEquals(3, lastLines.size());
        assertEquals("line1", lastLines.get(0));
        assertEquals("line2", lastLines.get(1));
        assertEquals("skip", lastLines.get(2));

        lastLines = logFile.getLastLines(2, "line");
        assertEquals(2, lastLines.size());

        // Test empty file
        logFile = new LogFile("./src/test/resources", "test2.log");
        lastLines = logFile.getLastLines(2, null);
        assertEquals(0, lastLines.size());
    }

    @Test
    public void testReverseByteInLine() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write('a');
        byteArrayOutputStream.write('b');
        byteArrayOutputStream.write('c');
        String str = LogFile.reverseByteInLine(byteArrayOutputStream);
        assertEquals("cba", str);

        // Test return line got removed
        byteArrayOutputStream.write('\n');
        str = LogFile.reverseByteInLine(byteArrayOutputStream);
        assertEquals("cba", str);
        byteArrayOutputStream.close();
    }
}