package com.example.log_collection;

import org.junit.Test;
import static org.junit.Assert.*;

public class LogRequestTest {

    @Test
    public void testGetPath() {
        assertEquals(Constants.DEFAULT_PATH, LogRequest.getPath(null));
        assertEquals(Constants.DEFAULT_PATH, LogRequest.getPath(""));

        String path = "abc";
        assertEquals(path, LogRequest.getPath(path));
    }

    @Test
    public void testValidFilename() {
        String message = LogRequest.validFilename("abc", "test1.log");
        assertNotNull(message);
        assertEquals(Constants.ErrorMessage.FILENAME_NOT_EXIST, message);

        message = LogRequest.validFilename("abc", null);
        assertNotNull(message);
        assertEquals(Constants.ErrorMessage.MISSING_FILENAME, message);

        assertNull(LogRequest.validFilename("./src/test/resources", "test1.log"));
    }

    @Test
    public void testGetEntries() {
        assertEquals(-1, LogRequest.getEntries("aa"));
        assertEquals(5, LogRequest.getEntries("5"));
    }
}
