package com.awesheet.handlers;

import com.awesheet.MainFrame;
import org.cef.CefApp;
import org.cef.CefApp.CefAppState;
import org.cef.callback.CefSchemeRegistrar;
import org.cef.handler.CefAppHandlerAdapter;

public class AppHandler extends CefAppHandlerAdapter {
    public AppHandler(String args[]) {
        super(args);
    }

    @Override
    public void onRegisterCustomSchemes(CefSchemeRegistrar registrar) {
        if (!registrar.addCustomScheme(MainSchemeHandler.scheme, true, false, false)) {
            MainFrame.getInstance().requestExit();
        }
    }

    @Override
    public void onContextInitialized() {
        CefApp cefApp = CefApp.getInstance();
        cefApp.registerSchemeHandlerFactory(MainSchemeHandler.scheme, MainSchemeHandler.domain, new SchemeHandlerFactory());
    }

    @Override
    public void stateHasChanged(CefAppState state) {
        // Shutdown the app if the native CEF part is terminated
        if (state == CefAppState.TERMINATED) {
            System.exit(0);
        }
    }
}
