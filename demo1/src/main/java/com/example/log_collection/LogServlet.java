package com.example.log_collection;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * This is the servlet class for log collection
 *
 * @author junyu.tian
 */
@WebServlet(
        name = "logServlet", value = "/log-collection"
)
public class LogServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(Constants.CONTENT_TYPE);
        String message;

        // Get and process request parameters
        String path = LogRequest.getPath(request.getParameter(Constants.PATH));
        String filename = request.getParameter(Constants.FILENAME);
        message = LogRequest.validFilename(path, filename);
        if (message != null) {
            LogResponse.buildResponse(response, message);
            return;
        }
        int entries = LogRequest.getEntries(request.getParameter(Constants.ENTRIES));
        if (entries < 0) {
            LogResponse.buildResponse(response, Constants.ErrorMessage.INVALID_ENTRIES);
            return;
        }
        String keyword = request.getParameter(Constants.KEYWORD);

        // Get the latest lines
        LogFile logFile = new LogFile(path, filename);
        List<String> latestLines = logFile.getLastLines(entries, keyword);

        // Display in UI
        if (keyword.length() > 0) {
            message = String.format("<h2>Last %d lines matched <mark>%s</mark> in %s</h2>",
                    latestLines.size(), keyword, filename);
        } else {
            message = String.format("<h2>Last %d lines in %s</h2>", latestLines.size(), filename);
        }
        LogResponse.buildResponse(response, message, latestLines);
    }

    public void destroy() {
    }
}