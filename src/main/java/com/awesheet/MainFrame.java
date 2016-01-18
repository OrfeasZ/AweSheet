package com.awesheet;

import com.awesheet.handlers.*;
import com.awesheet.managers.*;
import javafx.application.Platform;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.OS;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefMessageRouter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;

    private CefClient cefClient;
    private CefBrowser cefBrowser;
    private Component browserUI;

    private MenuHandler menuHandler;

    private boolean hasManagers;

    public static MainFrame getInstance() {
        return instance;
    }

    public static void createInstance(String args[]) {
        if (instance != null) {
            return;
        }

        instance = new MainFrame(args);
    }

    private MainFrame(String args[]) {
        hasManagers = false;

        initCEF(args);
        initWindow();
    }

    public MenuHandler getMenuHandler() {
        return menuHandler;
    }

    public void requestExit() {
        if (hasManagers) {
            return;
        }

        Platform.exit();
        JOptionPane.showMessageDialog(null, "AweSheet failed to initialize and will now exit.\nPlease make sure that your data isn't corrupted and try again.", "Initialization Error", JOptionPane.ERROR_MESSAGE);
    }

    public void initManagers() {
        if (hasManagers) {
            return;
        }

        hasManagers = true;

        // Create all the manager instances.
        UIMessageManager.getInstance();
        CSVManager.getInstance();
        FileManager.getInstance();
        HelpManager.getInstance();
        WindowManager.getInstance();
        FunctionManager.getInstance();
        WorkbookManager.getInstance();

        // Set manager properties and perform post-initialization.
        WindowManager.getInstance().setMainFrame(this);
        WindowManager.getInstance().setMenuHandler(menuHandler);

        UIMessageManager.getInstance().setBrowser(cefBrowser);

        FunctionManager.getInstance().setUIFunctions();
        WorkbookManager.getInstance().init();
    }

    private void initCEF(String args[]) {
        CefSettings settings = new CefSettings();
        settings.windowless_rendering_enabled = OS.isLinux();
        settings.remote_debugging_port = 8884;
        settings.log_severity = CefSettings.LogSeverity.LOGSEVERITY_DISABLE;

        // Add our custom app handler.
        CefApp cefApp_ = CefApp.getInstance(settings);
        CefApp.addAppHandler(new AppHandler(args));

        cefClient = cefApp_.createClient();

        // Register our custom message handler.
        CefMessageRouter router = CefMessageRouter.create(new CefMessageRouter.CefMessageRouterConfig("aweQuery", "aweQueryAbort"), new MessageRouterHandler());
        cefClient.addMessageRouter(router);
        cefClient.addContextMenuHandler(new ContextMenuHandler());
        cefClient.addLoadHandler(new LoadHandler());

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

    public static void main(String args[]) {
        // Make things look "native".
        JFrame.setDefaultLookAndFeelDecorated(false);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        MainFrame.createInstance(args);
    }
}
