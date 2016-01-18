package com.awesheet.ui.actions;

import com.awesheet.enums.UIMessageType;
import com.awesheet.managers.UIMessageManager;
import com.awesheet.messages.UIMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveWorkbookAction extends AbstractAction {
    public SaveWorkbookAction() {
        super("Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UIMessageManager.getInstance().onMessage(new UIMessage(UIMessageType.SAVE_WORKBOOK));
    }
}
