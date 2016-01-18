package com.awesheet.managers;

import com.awesheet.actions.SetHelpArticlesAction;
import com.awesheet.interfaces.IMessageListener;
import com.awesheet.messages.UIMessage;
import com.awesheet.models.HelpArticle;
import com.awesheet.ui.UIHelpArticle;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class HelpManager {
    private static HelpManager instance = null;

    protected HashMap<Integer, HelpArticle> articles;

    public static HelpManager getInstance() {
        if (instance == null) {
            instance = new HelpManager();
        }

        return instance;
    }

    protected HelpManager() {
        articles = new HashMap<Integer, HelpArticle>();
    }

    public void initHelpArticles() {
        // TODO: Currently everything is embedded here. We should probably move these to resources.
        articles.put(0, new HelpArticle(0, "Creating a new Workbook",
                "To create a new workbook you can can either click on the \"New\" button under the \"File\" menu, or press the \"Ctrl+N\" shortcut.\n" +
                "\n" +
                "If you already have another active workbook you will be presented with the following options:\n" +
                "1. Save and Continue, which when selected saves the Workbook and creates a new one.\n" +
                "2. Discard Changes, which when selected discards any pending changes and creates a new workbook.\n" +
                "3. Or Cancel, which when selected cancels the action."));

        articles.put(1, new HelpArticle(1, "Saving your Workbook",
                "To save the workbook you're currently editing you can either click on the \"Save\" or \"Save As\" buttons under the \"File\" menu, or use the \"Ctrl+S\" and \"Ctrl+Shift+S\" shortcuts.\n" +
                "\n" +
                "If the workbook you're editing has not been saved before you will be prompted to specify a storage location."));

        articles.put(2, new HelpArticle(2, "Opening a Workbook",
                "To open a previously saved workbook you can either click on the \"Open\" button under the \"File\" menu, or use the \"Ctrl+O\" shortcut. " +
                "You will then be prompted with a dialog where you will have to select the workbook you wish to open."));

        articles.put(3, new HelpArticle(3, "Creating and Deleting Sheets",
                "To create a sheet simply click on the \"+\" icon present at the bottom tab navigation.\n" +
                "To delete a sheet simply hover over it, and click on the \"x\" button that will appear.\n" +
                "\n" +
                "Note: You cannot delete the currently edited sheet."));

        articles.put(4, new HelpArticle(4, "Importing a Sheet",
                "AweSheet allows you to easily import sheets from CSV files in order to allow cross-compatibility with other applications.\n" +
                "\n" +
                "To import a sheet, simply click on the \"Import\" button under the \"File\" menu, and select a CSV file of your choice.\n" +
                "\n" +
                "Note: Importing a CSV file will overwrite your currently active sheet."));

        articles.put(5, new HelpArticle(5, "Exporting a Sheet",
                "AweSheet allows you to easily export your currently active sheet as a CSV file in order to allow cross-compatibility with other applications.\n" +
                "\n" +
                "To export your sheet, simply click on the \"Export\" button under the \"File\" menu, and select a destination file for the export."));

        articles.put(6, new HelpArticle(6, "Editing a Cell",
                "To edit a cell you can either double click on it, or selecting and start typing on the text box present in the top toolbar.\n" +
                "\n" +
                "After you've finished your entry you can confirm the input by hitting ENTER (or the \"check\" button in the top toolbar), or revert to the previous value by hitting ESC (or the \"cancel\" button in the top toolbar)."));

        articles.put(7, new HelpArticle(7, "Inserting Functions",
                "In AweSheet you can insert functions in two different ways: by typing the function directly, or by using the function insertion dialog.\n" +
                "\n" +
                "To add a function using the insertion dialog, simply select a cell and press the \"lightning\" button in the top toolbar. You can then follow the on-screen details in order to fill in the required fields for the function you're trying to insert. " +
                "Alternatively, you can insert a function manually when editing a cell.\n" +
                "\n" +
                "All functions in AweSheet follow the following pattern:\n" +
                "=function(parameter1, parameters2, ...)\n" +
                "\n" +
                "Where \"function\" is the function name, and \"parameterN\" is one of the following:\n" +
                "1. A cell (ie. A2).\n" +
                "2. Another function.\n" +
                "3. A generic value."));

        articles.put(8, new HelpArticle(8, "Creating Charts",
                "AweSheet supports the creation of two types of charts: Bar charts, and Line Charts.\n" +
                "\n" +
                "To create a chart, you will first need to select the cells that contain the data for the given chart.\n" +
                "\n" +
                "Data should be formatted in the following manner:\n" +
                "\n" +
                "[ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp] [ entry 1 ] [ entry 2 ]\n" +
                "[ label 1 ] [ value 1 ] [ value 1 ]\n" +
                "[ label 2 ] [ value 2 ] [ value 2 ]\n" +
                "[ label 3 ] [ value 3 ] [ value 3 ]\n" +
                "\n" +
                "Afterwards, you can select the type of chart you want to create by clicking on either \"Create Bar Chart\" or  \"Create Line Chart\" under the \"Chart\" menu. " +
                "You will then be prompted to fill in some extra (optional) details for your chart. Once you're satisfied with your input, you can click on \"Create Chart\" to generate the chart."));

        // Register help articles with the UI.
        HashMap<Integer, UIHelpArticle> uiArticles = new HashMap<Integer, UIHelpArticle>();

        for (HelpArticle article : articles.values()) {
            uiArticles.put(article.getID(), (UIHelpArticle) article.bind());
        }

        UIMessageManager.getInstance().dispatchAction(new SetHelpArticlesAction(uiArticles));
    }

    public String getOnlineURL() {
        // TODO
        return "http://google.com";
    }

    public Collection<HelpArticle> getHelpArticles() {
        return articles.values();
    }

    public HelpArticle getHelpArticle(int id) {
        if (!articles.containsKey(id)) {
            return null;
        }

        return articles.get(id);
    }
}
