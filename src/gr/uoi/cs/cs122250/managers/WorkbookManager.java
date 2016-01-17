package gr.uoi.cs.cs122250.managers;

import gr.uoi.cs.cs122250.enums.OpenResult;
import gr.uoi.cs.cs122250.enums.SaveResult;
import gr.uoi.cs.cs122250.interfaces.IMessageListener;
import gr.uoi.cs.cs122250.messages.UIMessage;
import gr.uoi.cs.cs122250.models.Workbook;

public class WorkbookManager implements IMessageListener {
    private static WorkbookManager instance = null;

    public static WorkbookManager getInstance() {
        if (instance == null) {
            instance = new WorkbookManager();
        }

        return instance;
    }

    protected Workbook currentWorkbook;

    protected WorkbookManager() {
        currentWorkbook = null;

        UIMessageManager.getInstance().registerListener(this);
    }

    public SaveResult saveWorkbook() {
        return SaveResult.ACCESS_ERROR;
    }

    public OpenResult openWorkbook(String path) {
        return OpenResult.ACCESS_ERROR;
    }

    public Workbook getCurrentWorkbook() {
        return currentWorkbook;
    }

    public void createWorkbook() {

    }

    @Override
    public void onMessage(UIMessage message) {

    }
}
