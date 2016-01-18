package com.awesheet.ui.actions;

import com.awesheet.models.Sheet;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SelectSheetAction extends AbstractAction {
    public SelectSheetAction(Sheet sheet) {
        super(sheet.getName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
