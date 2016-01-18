package com.awesheet.handlers;

import com.awesheet.MainFrame;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefLoadHandler;

public class LoadHandler implements CefLoadHandler {
    @Override
    public void onLoadingStateChange(CefBrowser cefBrowser, boolean isLoading, boolean canGoBack, boolean canGoForward) {

    }

    @Override
    public void onLoadStart(CefBrowser cefBrowser, int frame) {

    }

    @Override
    public void onLoadEnd(CefBrowser cefBrowser, int frame, int httpStatusCode) {
        // If we failed to load then request exit.
        // If managers are not initialized the application will exit.
        if (httpStatusCode != 200) {
            MainFrame.getInstance().requestExit();
            return;
        }

        // Init our managers once the page has finished loading.
        MainFrame.getInstance().initManagers();
    }

    @Override
    public void onLoadError(CefBrowser cefBrowser, int frame, ErrorCode errorCode, String errorText, String failedUrl) {

    }
}
