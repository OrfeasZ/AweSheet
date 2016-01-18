package com.awesheet.managers;

import com.awesheet.enums.UIMessageType;
import com.awesheet.handlers.MenuHandler;
import com.awesheet.interfaces.IMessageListener;
import com.awesheet.messages.UIMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.renderable.RenderableImageProducer;

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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    public void minimizeWindow() {
        mainFrame.setState(Frame.ICONIFIED);
    }

    public void maximizeWindow() {
        mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public void showMessageBox(String title, String contents, int messageType) {
        JOptionPane.showMessageDialog(null, contents, title, messageType);
    }

    @Override
    public void onMessage(UIMessage message) {
        switch (message.getType()) {
            case UIMessageType.EXIT_APPLICATION: {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        WorkbookManager.getInstance().saveWorkbook();
                        closeWindow();
                    }
                });

                break;
            }

            case UIMessageType.MAXIMIZE_APPLICATION: {
                maximizeWindow();
                break;
            }
        }
    }
}
