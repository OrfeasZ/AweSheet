package gr.uoi.cs.cs122250;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import gr.uoi.cs.cs122250.handlers.AppHandler;
import gr.uoi.cs.cs122250.handlers.ContextMenuHandler;
import gr.uoi.cs.cs122250.handlers.MessageRouterHandler;
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

    private JMenuBar menuBar;

    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu viewMenu;
    private JMenu chartMenu;
    private JMenu helpMenu;

    private MainFrame(String[] args) {
        initCEF(args);
        initMenus();
        initWindow();
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

    private void initMenus() {
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(600, 30));

        // File menu.
        fileMenu = new JMenu("   File   ");

        JMenuItem fileNewItem = new JMenuItem("New");
        fileNewItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        fileNewItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem fileOpenItem = new JMenuItem("Open");
        fileOpenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        fileOpenItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem fileSaveItem = new JMenuItem("Save");
        fileSaveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        fileSaveItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem fileSaveAsItem = new JMenuItem("Save As...      ");
        fileSaveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        fileSaveAsItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem fileImportItem = new JMenuItem("Import");
        fileImportItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem fileExportItem = new JMenuItem("Export");
        fileExportItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem fileExitItem = new JMenuItem("Exit");
        fileExitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        fileExitItem.setPreferredSize(new Dimension(200, 26));

        fileMenu.add(fileNewItem);
        fileMenu.add(fileOpenItem);
        fileMenu.add(fileSaveItem);
        fileMenu.add(fileSaveAsItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileImportItem);
        fileMenu.add(fileExportItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileExitItem);

        // Edit menu.
        editMenu = new JMenu("   Edit   ");

        JMenuItem editCopyItem = new JMenuItem("Copy");
        editCopyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        editCopyItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem editCutItem = new JMenuItem("Cut");
        editCutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        editCutItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem editPasteItem = new JMenuItem("Paste");
        editPasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        editPasteItem.setPreferredSize(new Dimension(200, 26));

        editMenu.add(editCopyItem);
        editMenu.add(editCutItem);
        editMenu.add(editPasteItem);

        // View menu.
        viewMenu = new JMenu("   View   ");

        JRadioButtonMenuItem viewSheet1Item = new JRadioButtonMenuItem("Sheet 1");
        viewSheet1Item.setMnemonic(KeyEvent.VK_F1);
        viewSheet1Item.setSelected(true);
        viewSheet1Item.setPreferredSize(new Dimension(200, 26));

        JRadioButtonMenuItem viewSheet2Item = new JRadioButtonMenuItem("Sheet 2");
        viewSheet2Item.setMnemonic(KeyEvent.VK_F2);
        viewSheet2Item.setPreferredSize(new Dimension(200, 26));

        JRadioButtonMenuItem viewSheet3Item = new JRadioButtonMenuItem("Sheet 3");
        viewSheet3Item.setMnemonic(KeyEvent.VK_F3);
        viewSheet3Item.setPreferredSize(new Dimension(200, 26));

        JMenuItem viewMaximizeItem = new JMenuItem("Maximize");
        viewMaximizeItem.setMnemonic(KeyEvent.VK_F11);
        viewMaximizeItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem viewMinimizeItem = new JMenuItem("Minimize");
        viewMinimizeItem.setPreferredSize(new Dimension(200, 26));

        viewMenu.add(viewSheet1Item);
        viewMenu.add(viewSheet2Item);
        viewMenu.add(viewSheet3Item);
        viewMenu.add(new JSeparator());
        viewMenu.add(viewMaximizeItem);
        viewMenu.add(viewMinimizeItem);

        // Chart menu.
        chartMenu = new JMenu("   Chart   ");

        JMenuItem chartBarItem = new JMenuItem("Create Bar Chart");
        chartBarItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem chartLineItem = new JMenuItem("Create Line Chart");
        chartLineItem.setPreferredSize(new Dimension(200, 26));

        chartMenu.add(chartBarItem);
        chartMenu.add(chartLineItem);

        // Help menu.
        helpMenu = new JMenu("   Help   ");

        JMenuItem helpDocumentationItem = new JMenuItem("Documentation");
        helpDocumentationItem.setMnemonic(KeyEvent.VK_F12);
        helpDocumentationItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem helpOnlineDocumentationItem = new JMenuItem("Online Documentation");
        helpOnlineDocumentationItem.setPreferredSize(new Dimension(200, 26));

        JMenuItem helpAboutItem = new JMenuItem("About AweSheet");
        helpAboutItem.setPreferredSize(new Dimension(200, 26));

        helpMenu.add(helpDocumentationItem);
        helpMenu.add(helpOnlineDocumentationItem);
        helpMenu.add(new JSeparator());
        helpMenu.add(helpAboutItem);

        // Add menus to menu bar.
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(chartMenu);
        menuBar.add(helpMenu);
    }

    private void initWindow() {
        setJMenuBar(menuBar);
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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        new MainFrame(args);
    }
}
