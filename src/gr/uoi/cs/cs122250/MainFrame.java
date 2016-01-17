package gr.uoi.cs.cs122250;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import gr.uoi.cs.cs122250.handlers.AppHandler;
import gr.uoi.cs.cs122250.handlers.ContextMenuHandler;
import gr.uoi.cs.cs122250.handlers.MenuHandler;
import gr.uoi.cs.cs122250.handlers.MessageRouterHandler;
import gr.uoi.cs.cs122250.managers.*;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.OS;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefMessageRouter;

public class MainFrame extends JFrame {
    private CefClient cefClient;
    private CefBrowser cefBrowser;
    private Component browserUI;
    private MenuHandler menuHandler;

    private MainFrame(String[] args) {
        initCEF(args);
        initManagers();
        initWindow();
    }

    private void initManagers() {
        UIMessageManager.getInstance();
        CSVManager.getInstance();
        FileManager.getInstance();
        HelpManager.getInstance();
        WindowManager.getInstance();
        WorkbookManager.getInstance();

        // Set manager properties.
        WindowManager.getInstance().setMainFrame(this);
        WindowManager.getInstance().setMenuHandler(menuHandler);
    }

    private void initCEF(String[] args) {
        CefSettings settings = new CefSettings();
        settings.windowless_rendering_enabled = OS.isLinux();
        settings.remote_debugging_port = 8884;

        // Add our custom app handler.
        CefApp cefApp_ = CefApp.getInstance(settings);
        CefApp.addAppHandler(new AppHandler(args));

        cefClient = cefApp_.createClient();

        // Register our custom message handler.
        CefMessageRouter router = CefMessageRouter.create(new CefMessageRouter.CefMessageRouterConfig("aweQuery", "aweQueryAbort"), new MessageRouterHandler());
        cefClient.addMessageRouter(router);
        cefClient.addContextMenuHandler(new ContextMenuHandler());

        // Create the browser.
        cefBrowser = cefClient.createBrowser("awe://sheet", settings.windowless_rendering_enabled, false);
        browserUI = cefBrowser.getUIComponent();
    }

    private void initWindow() {
        menuHandler = new MenuHandler();
        setJMenuBar(menuHandler.getMenuBar());

        getContentPane().add(browserUI, BorderLayout.CENTER);
        pack();

        setMinimumSize(new Dimension(600, 400));
        setSize(1280, 720);
        setVisible(true);
        setTitle("AweSheet");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Shutdown CEF if the window is closed.
                CefApp.getInstance().dispose();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        // Make things look "native".
        JFrame.setDefaultLookAndFeelDecorated(false);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        new MainFrame(args);
    }
}
