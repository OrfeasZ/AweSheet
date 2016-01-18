package com.awesheet.ui.actions;

import com.awesheet.enums.UIMessageType;
import com.awesheet.managers.UIMessageManager;
import com.awesheet.messages.UIMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PasteCellAction extends AbstractAction {
    public PasteCellAction() {
        super("Paste");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UIMessageManager.getInstance().onMessage(new UIMessage(UIMessageType.PASTE_CELL));
    }
}
