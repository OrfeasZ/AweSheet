package gr.uoi.cs.cs122250.managers;

import gr.uoi.cs.cs122250.models.Sheet;

public class CSVManager {
    private static CSVManager instance = null;

    public static CSVManager getInstance() {
        if (instance == null) {
            instance = new CSVManager();
        }

        return instance;
    }

    protected CSVManager() {
    }

    public Sheet importSheet(String path) {
        return null;
    }

    public boolean exportSheet(Sheet sheet, String path) {
        return false;
    }
}
