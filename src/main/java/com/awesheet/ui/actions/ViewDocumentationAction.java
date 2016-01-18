package com.awesheet.ui.actions;

import com.awesheet.actions.ShowPopupAction;
import com.awesheet.actions.popups.ChartPopup;
import com.awesheet.enums.UIPopupType;
import com.awesheet.managers.UIMessageManager;
import org.apache.commons.codec.binary.Base64;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ViewDocumentationAction extends AbstractAction {
    public ViewDocumentationAction() {
        super("Documentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UIMessageManager.getInstance().dispatchAction(new ShowPopupAction<String>(UIPopupType.HELP_POPUP, ""));
    }
}
