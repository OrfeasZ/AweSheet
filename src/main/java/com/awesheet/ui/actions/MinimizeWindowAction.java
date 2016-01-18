package com.awesheet.ui.actions;

import com.awesheet.managers.WindowManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MinimizeWindowAction extends AbstractAction {
    public MinimizeWindowAction() {
        super("Minimize");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowManager.getInstance().minimizeWindow();
    }
}
