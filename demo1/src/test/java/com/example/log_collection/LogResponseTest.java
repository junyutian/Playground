package com.example.log_collection;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LogResponseTest {

    @Test
    public void testBuildResponse() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        String message = "error";
        LogResponse.buildResponse(response, message);
        writer.flush();
        assertTrue(stringWriter.toString().contains(message));

        response = mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        message = "success";
        String line = "line1";
        List<String> lines = Collections.singletonList("line1");
        LogResponse.buildResponse(response, message, lines);
        writer.flush();
        assertTrue(stringWriter.toString().contains(message));
        assertTrue(stringWriter.toString().contains(line));
        writer.close();
    }
}
