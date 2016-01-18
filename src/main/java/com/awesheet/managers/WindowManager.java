/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
