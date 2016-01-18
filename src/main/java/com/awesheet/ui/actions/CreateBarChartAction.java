package com.awesheet.ui.actions;

import com.awesheet.actions.ShowPopupAction;
import com.awesheet.actions.popups.CreateChartPopup;
import com.awesheet.enums.ChartType;
import com.awesheet.enums.UIPopupType;
import com.awesheet.managers.UIMessageManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreateBarChartAction extends AbstractAction {
    public CreateBarChartAction() {
        super("Create Bar Chart");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UIMessageManager.getInstance().dispatchAction(
                new ShowPopupAction<CreateChartPopup>(UIPopupType.CREATE_CHART_POPUP,
                        new CreateChartPopup(ChartType.BAR_CHART_TYPE)));
    }
}
