package com.awesheet.ui.actions;

import com.awesheet.enums.UIMessageType;
import com.awesheet.managers.UIMessageManager;
import com.awesheet.messages.UIMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CutCellAction extends AbstractAction {
    public CutCellAction() {
        super("Cut");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UIMessageManager.getInstance().onMessage(new UIMessage(UIMessageType.CUT_CELL));
    }
}
