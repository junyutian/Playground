package com.example.log_collection;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * This is the class to build the responses
 *
 * @author junyu.tian
 */
public class LogResponse {
    /**
     * Build invalid response with error message
     *
     * @param response http servlet response
     * @param message error message
     * @throws IOException
     */
    public static void buildResponse(HttpServletResponse response, String message) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(message);
        out.println("</body></html>");
    }

    /**
     * Build valid response with headline message
     *
     * @param response http servlet response
     * @param message headline message
     * @param latestLines list of latest lines in log
     * @throws IOException
     */
    public static void buildResponse(HttpServletResponse response, String message,
                                     List<String> latestLines) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(message);
        for (String line : latestLines) {
            out.println("<br>" + line + "</br>");
        }
        out.println("</body></html>");
    }
}
