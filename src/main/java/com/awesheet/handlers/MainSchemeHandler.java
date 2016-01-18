package com.awesheet.handlers;

import com.awesheet.managers.FileManager;
import org.cef.callback.CefCallback;
import org.cef.handler.CefResourceHandlerAdapter;
import org.cef.misc.IntRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainSchemeHandler extends CefResourceHandlerAdapter {
    public static final String scheme = "awe";
    public static final String domain = "sheet";

    private boolean hasData;
    private byte[] fileData;
    private String mimeType;
    private int currentDataOffset = 0;

    public MainSchemeHandler() {
        super();
    }

    @Override
    public synchronized boolean processRequest(CefRequest request,
                                               CefCallback callback) {
        hasData = false;
        URI uri;

        // Parse the URI.
        try {
            uri = new URI(request.getURL());
        } catch (URISyntaxException e) {
            callback.Continue();
            return true;
        }

        if (!uri.getHost().equals("sheet")) {
            callback.Continue();
            return true;
        }

        // Form the file path.
        String path = uri.getPath();

        if (path.endsWith("/")) {
            path += "index.html";
        }

        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        if (path.length() == 0) {
            callback.Continue();
            return true;
        }

        String workingDir = System.getProperty("user.dir");
        Path webDir = Paths.get(workingDir, "web");

        // Combine working dir with requested path.
        Path finalPath = Paths.get(webDir.toString(), path);

        // Prevent directory traversal.
        if (!finalPath.startsWith(webDir)) {
            callback.Continue();
            return true;
        }

        // Try to read the file.
        fileData = FileManager.getInstance().readFile(finalPath.toString());

        if (fileData == null) {
            callback.Continue();
            return true;
        }

        // Get the file extension.
        String extension = "";

        int dotIndex = finalPath.toString().lastIndexOf('.');

        if (dotIndex > 0) {
            extension = finalPath.toString().substring(dotIndex + 1);
        }

        // Set mime type based on extension.
        if (extension.equals("html") || extension.equals("htm")) {
            mimeType = "text/html";
        } else if (extension.equals("json")) {
            mimeType = "application/json";
        } else if (extension.equals("js")) {
            mimeType = "application/javascript";
        } else if (extension.equals("css")) {
            mimeType = "text/css";
        } else if (extension.equals("png")) {
            mimeType = "image/png";
        } else if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("jpe")) {
            mimeType = "image/jpeg";
        } else if (extension.equals("gif")) {
            mimeType = "image/gif";
        } else if (extension.equals("bmp")) {
            mimeType = "image/bmp";
        } else if (extension.equals("ico")) {
            mimeType = "image/x-icon";
        } else if (extension.equals("txt") || extension.equals("obj")) {
            mimeType = "text/plan";
        } else if (extension.equals("svg")) {
            mimeType = "image/svg+xml";
        } else if (extension.equals("ttf") || extension.equals("otf")) {
            mimeType = "application/font-sfnt";
        } else if (extension.equals("woff")) {
            mimeType = "application/x-font-woff";
        } else if (extension.equals("woff2")) {
            mimeType = "application/font-woff2";
        } else if (extension.equals("ogv")) {
            mimeType = "video/ogg";
        } else if (extension.equals("ogg")) {
            mimeType = "audio/ogg";
        } else {
            mimeType = "application/octet-stream";
        }

        hasData = true;
        callback.Continue();
        return true;
    }

    @Override
    public void getResponseHeaders(CefResponse response,
                                   IntRef responseLength,
                                   StringRef redirectUrl) {
        if (!hasData) {
            responseLength.set(0);
            response.setStatus(404);
            return;
        }

        responseLength.set(fileData.length);
        response.setMimeType(mimeType);
        response.setStatus(200);
    }

    @Override
    public synchronized boolean readResponse(byte[] outputData,
                                             int bytesToRead,
                                             IntRef bytesRead,
                                             CefCallback callback) {
        if (!hasData) {
            bytesRead.set(0);
            return true;
        }

        if (currentDataOffset >= fileData.length) {
            currentDataOffset = 0;
            bytesRead.set(0);

            return false;
        }

        int transferSize = Math.min(bytesToRead, (fileData.length - currentDataOffset));
        System.arraycopy(fileData, currentDataOffset, outputData, 0, transferSize);
        currentDataOffset += transferSize;

        bytesRead.set(transferSize);

        return true;
    }
}
