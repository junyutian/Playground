package com.example.log_collection;

import java.io.*;

import javax.servlet.http.*;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LogServletTest {

    @Test
    public void testLogServlet() throws IOException {
        // Test normal case
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("path")).thenReturn("./src/test/resources");
        when(request.getParameter("filename")).thenReturn("test1.log");
        when(request.getParameter("keyword")).thenReturn("line");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new LogServlet().doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("Last 2 lines matched"));
        writer.close();

        // Test abnormal case
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        when(request.getParameter("filename")).thenReturn("test1.log");
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new LogServlet().doGet(request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains("Filename is not exist"));
        writer.close();
    }
}
