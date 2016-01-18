package com.awesheet.ui.actions;

import com.awesheet.managers.HelpManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;

public class ViewOnlineDocumentationAction extends AbstractAction {
    public ViewOnlineDocumentationAction() {
        super("Online Documentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop == null || !desktop.isSupported(Desktop.Action.BROWSE)) {
            return;
        }

        try {
            desktop.browse(new URI(HelpManager.getInstance().getOnlineURL()));
        } catch (Exception ignored) {
        }
    }
}
