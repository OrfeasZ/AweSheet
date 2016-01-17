package gr.uoi.cs.cs122250.managers;

import gr.uoi.cs.cs122250.interfaces.IMessageListener;
import gr.uoi.cs.cs122250.models.HelpArticle;

import java.util.HashSet;

public class HelpManager implements IMessageListener {
    private static HelpManager instance = null;

    protected HashSet<HelpArticle> articles;

    public static HelpManager getInstance() {
        if (instance == null) {
            instance = new HelpManager();
        }

        return instance;
    }

    protected HelpManager() {
        articles = new HashSet<HelpArticle>();
    }

    public String getOnlineURL() {
        return "";
    }

    public HashSet<HelpArticle> getHelpArticles() {
        return articles;
    }

    public HelpArticle getHelpArticle(int id) {
        return null;
    }

    @Override
    public void onMessage(UIMessage message) {

    }
}
