package gr.uoi.cs.cs122250.managers;

import gr.uoi.cs.cs122250.handlers.MenuHandler;
import gr.uoi.cs.cs122250.interfaces.IMessageListener;
import gr.uoi.cs.cs122250.messages.UIMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class WindowManager implements IMessageListener {
    private static WindowManager instance = null;

    protected JFrame mainFrame;
    protected MenuHandler menuHandler;

    public static WindowManager getInstance() {
        if (instance == null) {
            instance = new WindowManager();
        }

        return instance;
    }

    protected WindowManager() {
        UIMessageManager.getInstance().registerListener(this);
    }

    public void setMainFrame(JFrame frame) {
        mainFrame = frame;
    }

    public void setMenuHandler(MenuHandler handler) {
        menuHandler = handler;
    }

    public void setWindowTitle(String title) {
        mainFrame.setTitle(title);
    }

    public String getWindowTitle() {
        return mainFrame.getTitle();
    }

    public void closeWindow() {
        mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
    }

    public void minimizeWindow() {
        mainFrame.setState(Frame.ICONIFIED);
    }

    public void maximizeWindow() {
        mainFrame.setState(Frame.MAXIMIZED_BOTH);
    }

    public void showMessageBox(String title, String contents, int messageType) {
        JOptionPane.showMessageDialog(null, contents, title, messageType);
    }

    @Override
    public void onMessage(UIMessage message) {

    }
}
