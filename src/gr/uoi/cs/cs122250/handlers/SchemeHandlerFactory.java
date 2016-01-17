package gr.uoi.cs.cs122250.handlers;

import org.cef.browser.CefBrowser;
import org.cef.callback.CefSchemeHandlerFactory;
import org.cef.handler.CefResourceHandler;
import org.cef.network.CefRequest;

public class SchemeHandlerFactory implements CefSchemeHandlerFactory {
    @Override
    public CefResourceHandler create(CefBrowser browser, String schemeName, CefRequest request) {
        if (schemeName.equals(MainSchemeHandler.scheme))
            return new MainSchemeHandler();

        return null;
    }
}
