
package gr.uoi.cs.cs122250.handlers;

import org.cef.CefApp;
import org.cef.CefApp.CefAppState;
import org.cef.callback.CefSchemeRegistrar;
import org.cef.handler.CefAppHandlerAdapter;

public class AppHandler extends CefAppHandlerAdapter {
    public AppHandler(String[] args) {
        super(args);
    }

    @Override
    public void onRegisterCustomSchemes(CefSchemeRegistrar registrar) {
        if (!registrar.addCustomScheme(MainSchemeHandler.scheme, true, false, false)) {
            // TODO: Show some error dialog?
            System.err.println("Failed to register scheme handler.");
            System.exit(0);
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
