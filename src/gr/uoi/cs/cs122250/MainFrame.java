package gr.uoi.cs.cs122250;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import gr.uoi.cs.cs122250.handlers.AppHandler;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.OS;
import org.cef.browser.CefBrowser;

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

        CefApp cefApp_ = CefApp.getInstance(settings);
        CefApp.addAppHandler(new AppHandler(args));

        cefClient = cefApp_.createClient();
        cefBrowser = cefClient.createBrowser("awe://sheet", settings.windowless_rendering_enabled, false);
        browserUI = cefBrowser.getUIComponent();
    }

    private void initMenus() {
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(600, 30));

        fileMenu = new JMenu("   File   ");

        fileMenu.add(new JMenuItem("Open"));

        editMenu = new JMenu("   Edit   ");

        viewMenu = new JMenu("   View   ");

        chartMenu = new JMenu("   Chart   ");

        helpMenu = new JMenu("   Help   ");

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
