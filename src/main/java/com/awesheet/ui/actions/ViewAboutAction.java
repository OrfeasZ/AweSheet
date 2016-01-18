package com.awesheet.ui.actions;

import com.awesheet.actions.ShowPopupAction;
import com.awesheet.enums.UIPopupType;
import com.awesheet.managers.UIMessageManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ViewAboutAction extends AbstractAction {
    public ViewAboutAction() {
        super("About");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UIMessageManager.getInstance().dispatchAction(new ShowPopupAction<String>(UIPopupType.ABOUT_POPUP, ""));
    }
}
