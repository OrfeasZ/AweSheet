package com.awesheet.ui.actions;

import com.awesheet.managers.WindowManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MaximizeWindowAction extends AbstractAction {
    public MaximizeWindowAction() {
        super("Maximize");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowManager.getInstance().maximizeWindow();
    }
}
