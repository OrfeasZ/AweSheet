package com.awesheet.ui.actions;

import com.awesheet.managers.WindowManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitApplicationAction extends AbstractAction {
    public ExitApplicationAction() {
        super("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowManager.getInstance().closeWindow();
    }
}
