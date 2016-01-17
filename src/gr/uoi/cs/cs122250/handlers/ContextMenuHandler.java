package gr.uoi.cs.cs122250.handlers;

import org.cef.browser.CefBrowser;
import org.cef.callback.CefContextMenuParams;
import org.cef.callback.CefMenuModel;
import org.cef.handler.CefContextMenuHandler;

public class ContextMenuHandler implements CefContextMenuHandler {
    @Override
    public void onBeforeContextMenu(CefBrowser cefBrowser, CefContextMenuParams cefContextMenuParams, CefMenuModel cefMenuModel) {
        cefMenuModel.clear();
    }

    @Override
    public boolean onContextMenuCommand(CefBrowser cefBrowser, CefContextMenuParams cefContextMenuParams, int i, int i1) {
        return false;
    }

    @Override
    public void onContextMenuDismissed(CefBrowser cefBrowser) {

    }
}
