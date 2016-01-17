package com.awesheet.handlers;

import com.awesheet.managers.UIMessageManager;
import org.cef.browser.CefBrowser;
import org.cef.callback.CefQueryCallback;
import org.cef.handler.CefMessageRouterHandlerAdapter;

public class MessageRouterHandler extends CefMessageRouterHandlerAdapter {
    @Override
    public boolean onQuery(CefBrowser cefBrowser, long queryId, String request, boolean presistent, CefQueryCallback cefQueryCallback) {
        UIMessageManager.getInstance().onMessage(request);
        cefQueryCallback.success("");
        return true;
    }
}
