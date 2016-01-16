package gr.uoi.cs.cs122250;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.cef.CefApp;
import org.cef.CefApp.CefAppState;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.OS;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefAppHandlerAdapter;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = -5570653778104813836L;
    private final CefApp     cefApp_;
    private final CefClient  client_;
    private final CefBrowser browser_;
    private final Component  browerUI_;

    private MainFrame(String startURL, boolean useOSR, boolean isTransparent) {
        CefApp.addAppHandler(new CefAppHandlerAdapter(null) {
            @Override
            public void stateHasChanged(org.cef.CefApp.CefAppState state) {
                // Shutdown the app if the native CEF part is terminated
                if (state == CefAppState.TERMINATED) {
                    System.exit(0);
                }
            }
        });

        CefSettings settings = new CefSettings();
        settings.windowless_rendering_enabled = useOSR;
        cefApp_ = CefApp.getInstance(settings);

        client_ = cefApp_.createClient();

        browser_ = client_.createBrowser(startURL, useOSR, isTransparent);
        browerUI_ = browser_.getUIComponent();

        getContentPane().add(browerUI_, BorderLayout.CENTER);
        pack();
        setSize(800,600);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CefApp.getInstance().dispose();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new MainFrame("http://www.google.com", OS.isLinux(), false);
    }
}
